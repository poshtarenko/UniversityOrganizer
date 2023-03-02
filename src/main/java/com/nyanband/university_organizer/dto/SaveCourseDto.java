package com.nyanband.university_organizer.dto;

import com.nyanband.university_organizer.validation.annotation.UniqueCourseNumberConstraint;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Value
@UniqueCourseNumberConstraint
public class SaveCourseDto {
    @Positive(message = "Course number must be positive")
    @Max(value = 6, message = "Max course number is 6")
    Integer number;

    @Positive(message = "User id must be positive")
    Long userId;
}
