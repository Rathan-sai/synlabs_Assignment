package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponseDto {
    private int id;
    private User user;
    private String resumeFileAddress;
    private String skills;
    private String education;
    private String experience;
}
