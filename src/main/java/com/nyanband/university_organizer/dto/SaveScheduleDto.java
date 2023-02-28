package com.nyanband.university_organizer.dto;

import lombok.Value;

import javax.validation.constraints.Positive;

@Value
public class SaveScheduleDto {
    @Positive(message = "Semester id must be positive")
    Long semesterId;
}
