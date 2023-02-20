package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public List<CourseDto> getUserCourses(Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        return courseService.getUserCourses(userId);
    }

    @PostMapping
    public void add(Authentication authentication,
                    @RequestBody Integer courseNumber) {
        CourseDto courseDto = new CourseDto(
                0L,
                courseNumber,
                ControllerUtils.getUserId(authentication),
                Collections.emptyList()
        );
        courseService.save(courseDto);
    }

    @PostMapping("/delete")
    public void delete(Authentication authentication,
                       @RequestBody Long courseId) {
        courseService.delete(courseId);
    }

}
