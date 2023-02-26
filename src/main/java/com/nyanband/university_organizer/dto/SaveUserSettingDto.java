package com.nyanband.university_organizer.dto;

import lombok.Value;

@Value
public class SaveUserSettingDto {
    Integer userId;
    Integer break_time;
    Integer lessonDuration;
}
