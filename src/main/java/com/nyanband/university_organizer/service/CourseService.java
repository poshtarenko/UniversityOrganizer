package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.SaveCourseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface CourseService {

    List<CourseDto> getUserCourses(long userId);

    boolean isCourseBelongsToUser(long courseId, long userId);

    CourseDto save(@Valid SaveCourseDto courseDto);

    void delete(long courseId);
}
