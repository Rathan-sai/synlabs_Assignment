package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequestDto {
    private String email;
    private String password;

    // getters and setters
}

