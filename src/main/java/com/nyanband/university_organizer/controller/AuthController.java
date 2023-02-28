package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.entity.enums.ERole;
import com.nyanband.university_organizer.entity.Role;
import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.repository.RoleRepository;
import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.security.jwt.JwtUtils;
import com.nyanband.university_organizer.security.pojo.JwtResponse;
import com.nyanband.university_organizer.security.pojo.AuthRequest;
import com.nyanband.university_organizer.security.pojo.MessageResponse;
import com.nyanband.university_organizer.security.userdetails.UserDetailsImpl;
import com.nyanband.university_organizer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "Authentication API")
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;
    JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRespository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRespository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/sign_in")
    @ApiOperation("Authentication")
    public ResponseEntity<?> authUser(@Valid @RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                roles)
        );
    }

    @PostMapping("/sign_up")
    @ApiOperation("Registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        User user = new User(
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );
        user.setRoles(Collections.singletonList(new Role(ERole.USER)));
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
