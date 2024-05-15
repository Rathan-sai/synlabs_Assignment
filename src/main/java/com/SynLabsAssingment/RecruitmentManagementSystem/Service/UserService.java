package com.SynLabsAssingment.RecruitmentManagementSystem.Service;

import com.SynLabsAssingment.RecruitmentManagementSystem.Converter.UserConverter;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.LoginRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.UserRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;
import com.SynLabsAssingment.RecruitmentManagementSystem.Repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public int signUp(UserRequestDto userRequestDto) {
        User user = UserConverter.userRequestDtoToUserConverter(userRequestDto);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

}
