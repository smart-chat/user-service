package org.smartchat.userservice.service;

import org.smartchat.userservice.dto.EmailAuth;
import org.smartchat.userservice.dto.UserDto;
import org.smartchat.userservice.dto.UsernameAuth;
import org.smartchat.userservice.exceptions.UserRegistrationException;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    List<UserDto> getUsers();
    boolean login(UsernameAuth auth);
    boolean login(EmailAuth auth);
    boolean changePassword(String username, String oldPassword, String newPassword);
}
