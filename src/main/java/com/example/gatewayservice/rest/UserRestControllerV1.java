package com.example.gatewayservice.rest;

import com.example.gatewayservice.dto.UserDto;
import com.example.gatewayservice.models.Role;
import com.example.gatewayservice.models.User;
import com.example.gatewayservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserRestControllerV1 {
    private static final Role USER_ROLE = new Role("USER_ROLE");
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findByID(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Validated
    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody @Valid UserDto userDto){
        User user = userDto.getUser();
        user.setRoles(Arrays.asList(USER_ROLE));
        user = userService.registerUser(user);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: "+userDto.getUsername()+" not found");
        }
        userDto = UserDto.fromUser(user);
        return ResponseEntity.ok(userDto);

    }
}
