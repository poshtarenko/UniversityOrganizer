package com.nyanband.university_organizer.dto;

import com.nyanband.university_organizer.entity.enums.ELessonType;
import com.nyanband.university_organizer.entity.enums.EWeakType;
import com.nyanband.university_organizer.entity.enums.EWeekday;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class SaveScheduleItemDto {
    @Positive(message = "Schedule id must be positive")
    Long scheduleId;

    @Positive(message = "Discipline id must be positive")
    Long disciplineId;

    @NotNull(message = "Lesson type can not be null")
    ELessonType lessonType;

    @NotNull(message = "Week type can not be null")
    EWeakType weakType;

    @NotNull(message = "Weekday can not be null")
    EWeekday weekday;

    @Positive(message = "Lesson number must be positive")
    @Max(value = 20, message = "Max lesson number is 20")
    Integer lessonNum;
}
