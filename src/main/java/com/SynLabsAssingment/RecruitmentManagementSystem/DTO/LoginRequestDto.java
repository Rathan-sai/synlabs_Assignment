package com.SynLabsAssingment.RecruitmentManagementSystem.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
    @NotNull
    String email;
    @NotNull
    String Password;
}
