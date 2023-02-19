package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    CourseRepository courseRepository;

    public List<Course> getUserCourses(long userId){
        return courseRepository.getCoursesByUserId(userId);
    }

}
