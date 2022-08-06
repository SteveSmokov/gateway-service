package com.example.gatewayservice.dto;

import com.example.gatewayservice.models.Role;
import com.example.gatewayservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    @NotEmpty(
            message = "Username is obligatory"
    )
    @Size(
            min = 4,
            max = 100,
            message = "Username should have at least 4 to 100 characters"
    )
    private String username;
    private String firstName;
    private String lastName;
    @NotEmpty(
            message = "Email address is obligatory"
    )
    @Size(
            min = 6,
            max = 255,
            message = "Email address should have at least 4 to 255 characters"
    )
    @Pattern(regexp="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;
    @NotEmpty(
            message = "Password is obligatory"
    )
    @Size(
            min = 6,
            max = 255,
            message = "Password should have at least 6 to 255 characters"
    )
    private String password;

    @JsonIgnore
    public User getUser(){
        User user = new User();
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
    @JsonIgnore
    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
