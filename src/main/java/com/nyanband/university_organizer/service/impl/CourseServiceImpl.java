package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.SaveCourseDto;
import com.nyanband.university_organizer.dto.mapper.CourseMapper;
import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.entity.Semester;
import com.nyanband.university_organizer.entity.User;
import com.nyanband.university_organizer.repository.CourseRepository;
import com.nyanband.university_organizer.repository.SemesterRepository;
import com.nyanband.university_organizer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    SemesterRepository semesterRepository;
    CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             SemesterRepository semesterRepository,
                             CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    @Transactional
    public List<CourseDto> getUserCourses(long userId) {
        return courseRepository.getCoursesByUserId(userId).stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isCourseBelongsToUser(long courseId, long userId) {
        return courseRepository.courseBelongsToUser(courseId, userId);
    }

    @Override
    @Transactional
    public CourseDto save(SaveCourseDto courseDto) {
        Course course = courseRepository.save(courseMapper.toEntity(courseDto));

        Semester semester1 = new Semester(1, course);
        Semester semester2 = new Semester(2, course);
        semesterRepository.save(semester1);
        semesterRepository.save(semester2);

        return courseMapper.toDto(course);
    }

    @Override
    @Transactional
    public void delete(long courseId) {
        courseRepository.deleteById(courseId);
    }

}
