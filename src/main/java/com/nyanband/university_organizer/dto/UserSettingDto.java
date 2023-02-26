package com.nyanband.university_organizer.dto;


import lombok.Value;

@Value
public class UserSettingDto {
    Long id;
    Long userId;
    Integer lessonDuration;
    Integer break_time;
}
