package com.nyanband.university_organizer.dto;

import lombok.Value;

import javax.validation.constraints.Positive;

@Value
public class SaveUserSettingDto {
    @Positive(message = "Break time must be positive")
    Integer breakTime;

    @Positive(message = "Lesson duration must be positive")
    Integer lessonDuration;
}
