package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestDto {
    private int userId;
    private String resumeFileAddress;
    private String skills;
    private String education;
    private String experience;
}
