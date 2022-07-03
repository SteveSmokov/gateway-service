package com.example.gatewayservice.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class AuthenticationRequestDto {
    @NotEmpty(
            message = "Username is obligatory"
    )
    @Size(
            min = 4,
            max = 100,
            message = "Username should have at least 4 to 100 characters"
    )
    private String username;
    @NotEmpty(
            message = "Password is obligatory"
    )
    @Size(
            min = 6,
            max = 255,
            message = "Password should have at least 6 to 255 characters"
    )
    private String password;

}
