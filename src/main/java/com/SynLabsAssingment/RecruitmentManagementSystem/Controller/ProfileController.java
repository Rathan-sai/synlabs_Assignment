package com.SynLabsAssingment.RecruitmentManagementSystem.Controller;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.ProfileRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.ProfileResponseDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.UserNoFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Profile;
import com.SynLabsAssingment.RecruitmentManagementSystem.Service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {

    private ProfileService profileService;

    @PostMapping("/profile")
    public ResponseEntity<String> createProfile(@RequestBody ProfileRequestDto profileDTO) throws UserNoFoundException {
        profileService.createProfile(profileDTO);
        return ResponseEntity.ok("Profile created successfully");
    }

    @PostMapping("/uploadResume")
    public String uploadResume(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId) throws IOException {
        profileService.uploadResume(file, userId);
        return "Resume uploaded successfully";
    }

    @GetMapping("/applicant/{applicant_id}")
    public ResponseEntity getApplicant(@PathVariable("applicant_id") int applicantId) {
        Profile profileResponseDto;
        try{
            profileResponseDto = profileService.getApplicant(applicantId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(profileResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/applicants")
    public List<Profile> getApplicants() {
        return profileService.getApplicants();
    }
}
