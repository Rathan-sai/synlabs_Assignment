package com.SynLabsAssingment.RecruitmentManagementSystem.Service;

import com.SynLabsAssingment.RecruitmentManagementSystem.Converter.JobConverter;
import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.JobRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.JobNotFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.UserNoFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Job;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;
import com.SynLabsAssingment.RecruitmentManagementSystem.Repository.JobRepository;
import com.SynLabsAssingment.RecruitmentManagementSystem.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public void createJob(JobRequestDto jobRequestDto) throws UserNoFoundException {
        Job job = JobConverter.jobRequestDtoToJob(jobRequestDto);
        Optional<User> optionalUser;
        User user;
        try{
            optionalUser = userRepository.findById(jobRequestDto.getId());
            user = optionalUser.get();
        }catch (Exception e){
            throw new UserNoFoundException("Invalid UserId");
        }
        job.setAdmin(user);
        jobRepository.save(job);
    }

    public Job getJob(int jobId) throws JobNotFoundException {
        Job job;
        Optional<Job>optionalJob;
        try{
            optionalJob = jobRepository.findById(jobId);
            job = optionalJob.get();
        }catch (Exception e){
            throw new JobNotFoundException("Invalid Job id");
        }
        return job;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job applyForJob(int id){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return job;
    }
}
