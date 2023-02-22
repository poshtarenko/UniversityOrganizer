package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.SaveCourseDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Api(description = "Course API")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @ApiOperation("Get all user courses")
    public List<CourseDto> getMyCourses() {
        long userId = ControllerUtils.getUserId();
        return courseService.getUserCourses(userId);
    }

    @PostMapping
    @ApiOperation("Create new course")
    public ResponseEntity<?> addCourse(@RequestBody Integer courseNumber) {
        SaveCourseDto courseDto = new SaveCourseDto(
                courseNumber,
                ControllerUtils.getUserId()
        );
        courseService.save(courseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete")
    @ApiOperation("Delete course by id")
    public ResponseEntity<?> deleteCourse(@RequestBody Long courseId) {
        long userId = ControllerUtils.getUserId();

        if (courseService.isCourseBelongsToUser(courseId, userId)) {
            courseService.delete(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Course does not exist or user dont have access on it");
        }
    }

}
