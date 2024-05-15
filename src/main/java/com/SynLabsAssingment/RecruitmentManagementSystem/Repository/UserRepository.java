package com.SynLabsAssingment.RecruitmentManagementSystem.Repository;

import com.SynLabsAssingment.RecruitmentManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
