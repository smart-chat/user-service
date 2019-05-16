package org.smartchat.userservice.web;

import io.swagger.annotations.ApiOperation;
import org.smartchat.userservice.constats.Version;
import org.smartchat.userservice.dto.ProfileDto;
import org.smartchat.userservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ApiOperation(value = "Get user profile", tags = {"Profile"})
    @GetMapping(path = "/user/{username}/profile", produces = Version.V1)
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        return new ResponseEntity<>(profileService.getProfile(username), HttpStatus.OK);
    }

    @ApiOperation(value = "Update user profile", tags = {"Profile"})
    @PutMapping(path = "/user/{username}/profile", consumes = Version.V1, produces = Version.V1)
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable String username, @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(username, profileDto), HttpStatus.ACCEPTED);
    }
}
