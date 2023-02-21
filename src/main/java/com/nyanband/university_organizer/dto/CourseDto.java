package com.nyanband.university_organizer.dto;

import lombok.Value;

import java.util.List;

@Value
public class CourseDto {
    Long id;
    Integer number;
    Long userId;
    List<SemesterDto> semesters;
}
