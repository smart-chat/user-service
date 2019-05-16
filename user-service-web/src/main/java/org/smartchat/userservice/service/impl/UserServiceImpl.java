package org.smartchat.userservice.service.impl;

import org.smartchat.userservice.dao.ProfileDao;
import org.smartchat.userservice.dao.UserDao;
import org.smartchat.userservice.dto.EmailAuth;
import org.smartchat.userservice.dto.UserDto;
import org.smartchat.userservice.dto.UsernameAuth;
import org.smartchat.userservice.exceptions.UserRegistrationException;
import org.smartchat.userservice.model.Profile;
import org.smartchat.userservice.model.User;
import org.smartchat.userservice.service.UserService;
import org.smartchat.userservice.utils.Converter;
import org.smartchat.userservice.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (!emailValidator(userDto.getEmail())) {
            throw new UserRegistrationException("Email [ " + userDto.getEmail() + " ] is invalid.");
        }

        User storedUsed = userDao.getUserByUsername(userDto.getUsername());

        if (storedUsed != null) {
            throw new UserRegistrationException("Username [" + userDto.getUsername() + "] is already exists.");
        }

        Date createdDate = new Date();
        Profile savedProfile = profileDao.save(new Profile());

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRegisteredDate(createdDate);
        user.setProfile(savedProfile);

        return hidePassword(userDao.save(user));
    }

    @Override
    public UserDto getUserById(long id) {
        return null;
    }

    @Override
    public boolean login(UsernameAuth auth) {
        return login(auth.getLogin(), auth.getPassword(), null, "username");
    }

    @Override
    public boolean login(EmailAuth auth) {
        return login(null, auth.getPassword(), auth.getEmail(), "email");
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = userDao.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        String encryptedOldPass = user.getPassword();
        String encryptedEnteredPass = getEncryptString(user.getUsername(), oldPassword);

        if (encryptedOldPass.equalsIgnoreCase(encryptedEnteredPass)) {
            user.setPassword(newPassword);
            user.setUpdatedDate(new Date());
            userDao.save(user);

            return true;
        }

        return false;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.findAll();
        List<UserDto> userDtoList = Converter.convertFrom(users, UserDto.class);

        userDtoList.forEach(userDto -> userDto.setPassword("****"));
        return userDtoList;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return hidePassword(userDao.getUserByUsername(username));
    }

    private UserDto hidePassword(User user) {
        UserDto userDto = Converter.convert(user, UserDto.class);
        userDto.setPassword("*****");

        return userDto;
    }

    private String getEncryptString(String username, String password) {
        String passWithSalt = username+password;

        return Encryptor.getHash(passWithSalt);
    }

    private boolean login(String username, String password, String email, String type) {
        User user = null;

        if (type.equalsIgnoreCase("username")) {
            user = userDao.getUserByUsername(username);
        } else if (type.equalsIgnoreCase("email")) {
            user = userDao.getUserByEmail(email);
        }

        if (user == null) {
            return false;
        }

        if (!user.getPassword().equalsIgnoreCase(getEncryptString(user.getUsername(), password))) {
            return false;
        }

        user.setLoginDate(new Date());
        userDao.save(user);
        return true;
    }

    private boolean emailValidator(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        System.out.println(email + " : " + matcher.matches());

        return matcher.matches();
    }
}
