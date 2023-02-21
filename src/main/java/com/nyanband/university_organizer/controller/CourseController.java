package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.security.pojo.MessageResponse;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CourseDto> getUserCourses(Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        return courseService.getUserCourses(userId);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> add(Authentication authentication,
                    @RequestBody Integer courseNumber) {
        CourseDto courseDto = new CourseDto(
                0L,
                courseNumber,
                ControllerUtils.getUserId(authentication),
                Collections.emptyList()
        );
        courseService.save(courseDto);
        return ControllerUtils.getOkResponse();
    }

    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> delete(Authentication authentication,
                       @RequestBody Long courseId) {
        long userId = ControllerUtils.getUserId(authentication);

        if (courseService.isCourseBelongsToUser(courseId, userId)) {
            courseService.delete(courseId);
            return ControllerUtils.getOkResponse();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: this course do not belong to current user"));
        }
    }

}
