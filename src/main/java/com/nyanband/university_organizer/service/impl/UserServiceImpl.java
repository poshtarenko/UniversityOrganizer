package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.entity.UserSetting;
import com.nyanband.university_organizer.entity.enums.ERole;
import com.nyanband.university_organizer.repository.RoleRepository;
import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.repository.UserSettingRepository;
import com.nyanband.university_organizer.security.pojo.AuthRequest;
import com.nyanband.university_organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    UserSettingRepository userSettingRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserSettingRepository userSettingRepository,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userSettingRepository = userSettingRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void register(AuthRequest authRequest) {
        User user = new User(
                authRequest.getEmail(),
                authRequest.getPassword(),
                Collections.singletonList(roleRepository.findByName(ERole.USER).orElseThrow(
                        () -> new RuntimeException("Role USER dont found")
                ))
        );
        userRepository.save(user);
        UserSetting userSetting = new UserSetting(1, 1, user);
        userSettingRepository.save(userSetting);
    }
}
