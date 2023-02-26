package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.UpdateUserSettingDto;
import com.nyanband.university_organizer.dto.UserSettingDto;
import com.nyanband.university_organizer.dto.mapper.UserSettingMapper;
import com.nyanband.university_organizer.entity.UserSetting;
import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.repository.UserSettingRepository;
import com.nyanband.university_organizer.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserSettingServiceImpl implements UserSettingService {

    UserSettingRepository userSettingRepository;
    UserSettingMapper userSettingMapper;
    UserRepository userRepository;
    @Autowired
    public UserSettingServiceImpl(UserSettingRepository userSettingRepository,
                                  UserSettingMapper userSettingMapper,
                                  UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userSettingMapper = userSettingMapper;
        this.userSettingRepository = userSettingRepository;

    }

    @Transactional
    @Override
    public void update(UpdateUserSettingDto userSettingDto) {
        UserSetting userSetting = userSettingRepository.getUserSettingByUserId(userSettingDto.getUserId());
        userSetting.setBreak_time(userSettingDto.getBreak_time());
        userSetting.setLesson_time(userSettingDto.getLessonDuration());
        userSettingRepository.save(userSetting);
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
