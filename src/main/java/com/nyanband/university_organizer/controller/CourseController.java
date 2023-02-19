package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    CourseService courseService;

    @GetMapping
    public List<Course> getUserCourses(@RequestBody Long userId) {
        return courseService.getUserCourses(userId);
    }

}
