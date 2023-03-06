package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.security.pojo.SignUpRequest;

public interface UserService {

    void register(SignUpRequest signUpRequest);
}
