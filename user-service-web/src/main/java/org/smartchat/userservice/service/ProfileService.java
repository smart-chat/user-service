package org.smartchat.userservice.service;

import org.smartchat.userservice.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto getProfile(String username);
    List<ProfileDto> allProfiles();
    ProfileDto updateProfile(String username, ProfileDto profileDto);
}
