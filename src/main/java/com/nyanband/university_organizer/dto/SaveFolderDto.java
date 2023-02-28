package com.nyanband.university_organizer.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Value
public class SaveFolderDto {
    @NotEmpty(message = "Name can not be empty")
    String name;

    @Positive(message = "Discipline id must be positive")
    Long disciplineId;
}
