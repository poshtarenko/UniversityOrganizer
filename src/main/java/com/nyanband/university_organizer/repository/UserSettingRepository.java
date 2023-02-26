package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.UserSetting;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {


    @Query("select count(us) > 0  from UserSetting  us  where  us.id = :settingId and us.user.id = :userId")
    Boolean SettingsBelongsToUser(long userId, long settingId);



    UserSetting getUserSettingByUserId(long userId);

}
