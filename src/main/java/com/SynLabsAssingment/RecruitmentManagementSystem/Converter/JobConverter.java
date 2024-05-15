package com.SynLabsAssingment.RecruitmentManagementSystem.Converter;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.JobRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Job;
import org.hibernate.type.descriptor.java.LocalTimeJavaType;

public class JobConverter {

    public static Job jobRequestDtoToJob(JobRequestDto jobRequestDto){
        return Job.builder()
                .title(jobRequestDto.getTitle())
                .companyName(jobRequestDto.getCompanyName())
                .description(jobRequestDto.getDescription())
                .build();
    }
}
