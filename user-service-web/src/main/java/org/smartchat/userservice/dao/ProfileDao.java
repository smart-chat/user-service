package org.smartchat.userservice.dao;

import org.smartchat.userservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository<Profile, Long> { }
