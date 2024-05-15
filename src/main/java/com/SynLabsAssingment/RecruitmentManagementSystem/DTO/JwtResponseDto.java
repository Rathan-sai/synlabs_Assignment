package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {
    private String token;

    private String user_name;

    public String getToken() {
        return token;
    }
}
