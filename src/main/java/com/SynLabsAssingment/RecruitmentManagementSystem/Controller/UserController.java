package com.SynLabsAssingment.RecruitmentManagementSystem.Controller;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.*;
import com.SynLabsAssingment.RecruitmentManagementSystem.Security.JwtHelper;
import com.SynLabsAssingment.RecruitmentManagementSystem.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("User")
@CrossOrigin
public class UserController {

    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<Integer> signUp(@RequestBody UserRequestDto userDTO) {
        int userId = userService.signUp(userDTO);
//        return ResponseEntity.ok("User signed up successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/profile")
                .body(userId);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtRequestDto request) {

        try{
            this.doAuthenticate(request.getEmail(), request.getPassword()); //this function will do the Authentication..


            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            String token = this.helper.generateToken(userDetails);

            JwtResponseDto response = JwtResponseDto.builder()
                    .token(token)
                    .user_name(userDetails.getUsername()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
