package com.SynLabsAssingment.RecruitmentManagementSystem.Converter;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.ProfileRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.ProfileResponseDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Profile;

public class ProfileConverter {

    public static Profile profileRequestDtoToProfile(ProfileRequestDto profileRequestDto){
        return Profile.builder()
                .education(profileRequestDto.getEducation())
                .skills(profileRequestDto.getSkills())
                .experience(profileRequestDto.getExperience())
                .build();
    }

    public static ProfileResponseDto profileToProfileResponseDto(Profile profile){
        return ProfileResponseDto.builder()
                .education(profile.getEducation())
                .id(profile.getId())
                .user(profile.getUser())
                .skills(profile.getSkills())
                .resumeFileAddress(profile.getResumeFileAddress())
                .build();
    }
}
