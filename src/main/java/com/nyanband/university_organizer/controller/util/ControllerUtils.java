package com.nyanband.university_organizer.controller.util;

import com.nyanband.university_organizer.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;

public class ControllerUtils {
    public static long getUserId(Authentication authentication) {
        return ((UserDetailsImpl) authentication.getPrincipal()).getId();
    }
}
