package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SaveUserSettingDto;
import com.nyanband.university_organizer.dto.UserSettingDto;
import com.nyanband.university_organizer.dto.mapper.UserSettingMapper;
import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.entity.UserSetting;
import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.repository.UserSettingRepository;
import com.nyanband.university_organizer.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserSettingImpl implements UserSettingService {

    UserSettingRepository userSettingRepository;
    UserSettingMapper userSettingMapper;
    UserRepository userRepository;
    @Autowired
    public UserSettingImpl(UserSettingRepository userSettingRepository,
                           UserSettingMapper userSettingMapper,
                           UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userSettingMapper = userSettingMapper;
        this.userSettingRepository = userSettingRepository;

    }

//    User userFromDb = userRepository.findById(u.getid());
//    // crush the variables of the object found
//    userFromDb.setFirstname("john");
//    userFromDb.setLastname("dew");
//    userFromDb.setAge(16);
//    userRepository.save(userFromDb);
    @Transactional
    @Override
    public void update(SaveUserSettingDto saveUserSettingDto) {
        //Optional<User> user = userRepository.findById(Long.valueOf(saveUserSettingDto.getUserId()));

        UserSetting userSetting = userSettingRepository.save(userSettingMapper.toEntity(saveUserSettingDto));
    }
    @Transactional
    @Override
    public boolean isSettingBelongsToUser(long userId,long settingId) {
        return userSettingRepository.SettingsBelongsToUser(userId,settingId);
    }



    @Transactional
    @Override
    public UserSettingDto getUserSetting(long userId) {

        return userSettingMapper.toDto(userSettingRepository.getUserSettingByUserId(userId));
    }



}
