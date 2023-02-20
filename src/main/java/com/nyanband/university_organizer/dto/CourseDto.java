package com.nyanband.university_organizer.dto;

import com.nyanband.university_organizer.entity.Semester;
import lombok.Value;

import java.util.List;

@Value
public class CourseDto {
    Long id;
    Integer number;
    Long userId;
    List<SemesterDto> semesters;
}
