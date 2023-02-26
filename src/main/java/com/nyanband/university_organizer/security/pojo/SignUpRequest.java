package com.nyanband.university_organizer.security.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {
    String email;
    String password;
    List<String> roles;
}
