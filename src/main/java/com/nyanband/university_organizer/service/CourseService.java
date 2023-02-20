package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dao.CourseDao;
import com.nyanband.university_organizer.dao.SemesterDao;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.mapper.CourseMapper;
import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.entity.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;

    @Autowired
    SemesterDao semesterDao;

    @Autowired
    CourseMapper courseMapper;

    @Transactional
    public List<CourseDto> getUserCourses(long userId) {
        return courseDao.getCoursesByUserId(userId).stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        courseDao.save(course);

        Semester semester1 = new Semester(1, course);
        Semester semester2 = new Semester(2, course);
        semesterDao.save(semester1);
        semesterDao.save(semester2);
    }

    @Transactional
    public void delete(long courseId) {
        courseDao.delete(courseId);
    }

}
