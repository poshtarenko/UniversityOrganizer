package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.UpdateUserSettingDto;
import com.nyanband.university_organizer.dto.UserSettingDto;

public interface UserSettingService {
    void update(UpdateUserSettingDto userSettingDto);

    boolean isSettingBelongsToUser(long userId, long settingId);

//    void save(SaveUserSettingDto userSettingDto);

    UserSettingDto getUserSetting(long userId);


}
