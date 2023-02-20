package com.nyanband.university_organizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class UniversityOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityOrganizerApplication.class, args);
    }

}