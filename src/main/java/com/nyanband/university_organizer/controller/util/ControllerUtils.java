package com.nyanband.university_organizer.controller.util;

import com.nyanband.university_organizer.security.userdetails.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class ControllerUtils {

    public static long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetailsImpl) authentication.getPrincipal()).getId();
    }

    public static ResponseEntity<HttpStatus> okResponse() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
