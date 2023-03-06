package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.security.jwt.JwtUtils;
import com.nyanband.university_organizer.security.pojo.JwtResponse;
import com.nyanband.university_organizer.security.pojo.SignInRequest;
import com.nyanband.university_organizer.security.pojo.SignUpRequest;
import com.nyanband.university_organizer.security.userdetails.UserDetailsImpl;
import com.nyanband.university_organizer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "Authentication API")
public class AuthController {

    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;
    UserService userService;
    JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/sign_in")
    @ApiOperation("Authentication")
    public ResponseEntity<?> authUser(@Valid @RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));

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
                        roles
                )
        );
    }

    @PostMapping("/sign_up")
    @ApiOperation("Registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userService.register(signUpRequest);
        return ControllerUtils.okResponse();
    }
}
