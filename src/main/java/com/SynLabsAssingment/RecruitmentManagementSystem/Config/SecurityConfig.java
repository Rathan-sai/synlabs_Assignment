package com.SynLabsAssingment.RecruitmentManagementSystem.Config;

import com.SynLabsAssingment.RecruitmentManagementSystem.Security.JwtAuthenticationEntryPoint;
import com.SynLabsAssingment.RecruitmentManagementSystem.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeRequests(auth ->auth
                .requestMatchers("/signup", "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/admin/job").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/admin/job/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/admin/applicants").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/admin/applicant/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/uploadResume").hasAuthority("APPLICANT")
                .requestMatchers(HttpMethod.GET, "/jobs").authenticated()
                .requestMatchers(HttpMethod.GET, "/jobs/apply").hasAuthority("APPLICANT")
                .anyRequest().authenticated())
                .exceptionHandling(exc -> exc.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
