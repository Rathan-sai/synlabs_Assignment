package com.SynLabsAssingment.RecruitmentManagementSystem.Converter;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.UserRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;

public class UserConverter {

    public static User userRequestDtoToUserConverter(UserRequestDto userRequestDto){
        return User.builder()
                .userType(userRequestDto.getUserType())
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .address(userRequestDto.getAddress())
                .password(userRequestDto.getPassword())
                .profileHeadline(userRequestDto.getProfileHeadline())
                .build();
    }
}
