package com.nyanband.university_organizer;

import com.nyanband.university_organizer.controller.error.RestExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class UniversityOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityOrganizerApplication.class, args);
    }

}