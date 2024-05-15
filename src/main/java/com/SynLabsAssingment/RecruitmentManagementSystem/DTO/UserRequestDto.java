package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import com.SynLabsAssingment.RecruitmentManagementSystem.Enum.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String address;
    private UserType userType;
    private String password;
    private String profileHeadline;
}
