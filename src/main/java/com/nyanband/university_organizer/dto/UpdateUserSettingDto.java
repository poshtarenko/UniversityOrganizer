package com.nyanband.university_organizer.dto;

import lombok.Value;

@Value
public class UpdateUserSettingDto {
    Long userId;
    Integer break_time;
    Integer lessonDuration;
}
