package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.SaveCourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> getUserCourses(long userId);

    boolean isCourseBelongsToUser(long courseId, long userId);

    CourseDto save(SaveCourseDto courseDto);

    void delete(long courseId);
}
