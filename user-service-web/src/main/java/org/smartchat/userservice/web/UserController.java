package org.smartchat.userservice.web;

import io.swagger.annotations.ApiOperation;
import org.smartchat.userservice.constats.Version;
import org.smartchat.userservice.dto.EmailAuth;
import org.smartchat.userservice.dto.UpdateUser;
import org.smartchat.userservice.dto.UsernameAuth;
import org.smartchat.userservice.dto.UserDto;
import org.smartchat.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Register new user", tags = {"User"})
    @PostMapping(path = "/user/registration", consumes = Version.V1)
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "List of all users", tags = {"User"})
    @GetMapping(path = "/users", produces = Version.V1)
    public ResponseEntity<List<UserDto>> allUsers() {
        List<UserDto> userDtoList = userService.getUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "List of all users", tags = {"User"})
    @GetMapping(path = "/users/{username}", produces = Version.V1)
    public ResponseEntity<UserDto> getUser(@RequestParam(name = "username") String username) {
        UserDto userDtoList = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @ApiOperation(value = "Login user by username", tags = {"User"})
    @PostMapping(path = "/login/username", consumes = Version.V1)
    public ResponseEntity<String> login(@RequestBody UsernameAuth userCred) {
        return new ResponseEntity<>(userService.login(userCred) ? "Success" : "Failed", HttpStatus.OK);
    }

    @ApiOperation(value = "Login user by email", tags = {"User"})
    @PostMapping(path = "/login/email", consumes = Version.V1)
    public ResponseEntity<String> login(@RequestBody EmailAuth userCred) {
        return new ResponseEntity<>(userService.login(userCred) ? "Success" : "Failed", HttpStatus.OK);
    }

    @ApiOperation(value = "Update user password", tags = {"User"})
    @PutMapping(path = "/user/update", consumes = Version.V1, produces = Version.V1)
    public ResponseEntity<String> changePassword(@RequestBody UpdateUser updatedUser) {
        return new ResponseEntity<>(userService
                .changePassword(updatedUser.getUsername(),
                        updatedUser.getOldPassword(),
                        updatedUser.getNewPassword()) ? "Success" : "Failed",
                HttpStatus.ACCEPTED);
    }
}
