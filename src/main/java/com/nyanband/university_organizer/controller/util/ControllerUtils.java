package com.nyanband.university_organizer.controller.util;

import com.nyanband.university_organizer.security.pojo.MessageResponse;
import com.nyanband.university_organizer.security.userdetails.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public class ControllerUtils {
    public static long getUserId(Authentication authentication) {
        return ((UserDetailsImpl) authentication.getPrincipal()).getId();
    }

}
