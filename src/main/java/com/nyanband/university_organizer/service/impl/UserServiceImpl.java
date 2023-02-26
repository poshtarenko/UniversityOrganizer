package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.entity.UserSetting;
import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.repository.UserSettingRepository;
import com.nyanband.university_organizer.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl  implements UserService {

    UserSettingRepository userSettingRepository;
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserSettingRepository userSettingRepository) {
        this.userRepository = userRepository;
        this.userSettingRepository = userSettingRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
        UserSetting userSetting = new UserSetting(0,0,user);
        userSettingRepository.save(userSetting);
    }
}
