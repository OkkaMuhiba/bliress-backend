package com.blibli.future.phase2.model.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank
    @Length(min = 8, max = 16)
    private String username;

    @NotBlank
    @Length(min = 8)
    private String password;

    @NotBlank
    @Email
    private String usermail;
}
