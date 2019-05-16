package org.smartchat.userservice.utils;

import org.smartchat.userservice.dto.UserDto;

public interface PreCheck {
    boolean apply(UserDto userDto);
}
