package com.SynLabsAssingment.RecruitmentManagementSystem.Models;

import com.SynLabsAssingment.RecruitmentManagementSystem.Enum.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String address;
    private UserType userType;
    private String password;
    private String profileHeadline;
}
