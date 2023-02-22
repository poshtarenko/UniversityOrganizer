package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.SaveCourseDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.security.pojo.MessageResponse;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getMyCourses(Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        return courseService.getUserCourses(userId);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(Authentication authentication,
                                       @RequestBody Integer courseNumber) {
        SaveCourseDto courseDto = new SaveCourseDto(
                courseNumber,
                ControllerUtils.getUserId(authentication)
        );
        courseService.save(courseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteCourse(Authentication authentication,
                                          @RequestBody Long courseId) {
        long userId = ControllerUtils.getUserId(authentication);

        if (courseService.isCourseBelongsToUser(courseId, userId)) {
            courseService.delete(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Course does not exist or user dont have access on it");
        }
    }

}
