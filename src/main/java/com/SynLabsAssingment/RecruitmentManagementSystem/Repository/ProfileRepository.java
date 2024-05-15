package com.SynLabsAssingment.RecruitmentManagementSystem.Repository;

import com.SynLabsAssingment.RecruitmentManagementSystem.Models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
