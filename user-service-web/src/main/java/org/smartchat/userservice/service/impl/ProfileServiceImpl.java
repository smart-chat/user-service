package org.smartchat.userservice.service.impl;

import org.smartchat.userservice.dao.ProfileDao;
import org.smartchat.userservice.dao.UserDao;
import org.smartchat.userservice.dto.ProfileDto;
import org.smartchat.userservice.model.Profile;
import org.smartchat.userservice.model.User;
import org.smartchat.userservice.service.ProfileService;
import org.smartchat.userservice.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public ProfileServiceImpl(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @Override
    public ProfileDto getProfile(String username) {
        User user = userDao.getUserByUsername(username);
        Profile profile = user.getProfile();

        return Converter.convert(profile, ProfileDto.class);
    }

    @Override
    public List<ProfileDto> allProfiles() {
        return null;
    }

    @Override
    public ProfileDto updateProfile(String username, ProfileDto profileDto) {
        User user = userDao.getUserByUsername(username);
        Profile profile = user.getProfile();

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setGender(profileDto.getGender());
        profile.setPhone(profileDto.getPhone());
        profile.setUpdatedDate(new Date());

        Profile savedProfile = profileDao.save(profile);

        return Converter.convert(savedProfile, ProfileDto.class);
    }
}
