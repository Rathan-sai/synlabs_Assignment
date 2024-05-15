package com.SynLabsAssingment.RecruitmentManagementSystem.Config;

import com.SynLabsAssingment.RecruitmentManagementSystem.Security.JwtAuthenticationEntryPoint;
import com.SynLabsAssingment.RecruitmentManagementSystem.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JwtAuthenticationEntryPoint point;
    private JwtAuthenticationFilter filter;

    @Autowired
    public SecurityConfig(JwtAuthenticationEntryPoint point, JwtAuthenticationFilter filter) {
        this.point = point;
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        //YOu have to do configuration..
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/home/**").authenticated()
                        .requestMatchers("/sunbase/**").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(("/customer/**")).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exc->exc.authenticationEntryPoint(point))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
