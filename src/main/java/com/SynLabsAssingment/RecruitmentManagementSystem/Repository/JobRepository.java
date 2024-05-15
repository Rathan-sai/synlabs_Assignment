package com.SynLabsAssingment.RecruitmentManagementSystem.Repository;

import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
