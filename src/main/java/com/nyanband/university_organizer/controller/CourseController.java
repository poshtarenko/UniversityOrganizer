package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private static final String PATH = "/courses";

    @Autowired
    CourseService courseService;

    @GetMapping(value = PATH)
    public List<Course> getUserCourses() {
        return courseService.getUserCourses(1);
    }

}
