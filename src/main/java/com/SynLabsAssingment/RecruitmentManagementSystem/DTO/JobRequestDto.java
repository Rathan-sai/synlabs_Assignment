package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRequestDto {
    private int id;
    private String title;
    private String description;
    private String companyName;
}
