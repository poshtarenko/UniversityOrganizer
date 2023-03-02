package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.security.pojo.AuthRequest;

public interface UserService {

    void register(AuthRequest authRequest);
}
