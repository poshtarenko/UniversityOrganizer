package com.nyanband.university_organizer.exception.util;

import com.nyanband.university_organizer.exception.error.ApiError;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ExceptionHandlerUtils {
    public static ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
