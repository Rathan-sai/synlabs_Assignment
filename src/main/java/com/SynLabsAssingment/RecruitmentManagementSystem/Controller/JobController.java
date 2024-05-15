package com.SynLabsAssingment.RecruitmentManagementSystem.Controller;

import com.SynLabsAssingment.RecruitmentManagementSystem.DTO.JobRequestDto;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.JobNotFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Exception.UserNoFoundException;
import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Job;
import com.SynLabsAssingment.RecruitmentManagementSystem.Service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class JobController {

    private JobService jobService;

    @PostMapping("/job")
    public ResponseEntity<String> createJob(@RequestBody JobRequestDto job) throws UserNoFoundException {
        jobService.createJob(job);
        return ResponseEntity.ok("Job created successfully");
    }

    @GetMapping("/job/{job_id}")
    public ResponseEntity<Job> getJob(@PathVariable("job_id") int jobId) throws JobNotFoundException {
        Job jobDTO = jobService.getJob(jobId);
        return ResponseEntity.ok(jobDTO);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/job/apply/{jobId}")
    public ResponseEntity<Job> applyForJob(@PathVariable("jobId") int jobId){
        Job job = jobService.applyForJob(jobId);
        return ResponseEntity.ok(job);
    }
}
