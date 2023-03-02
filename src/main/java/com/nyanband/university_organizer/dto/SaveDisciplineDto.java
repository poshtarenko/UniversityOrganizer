package com.nyanband.university_organizer.dto;

import com.nyanband.university_organizer.validation.annotation.UniqueDisciplineNameConstraint;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Value
@UniqueDisciplineNameConstraint
public class SaveDisciplineDto {
    @NotEmpty(message = "Name can not be empty")
    String name;

    @Positive(message = "Semester id must be positive")
    Long semesterId;
}
