package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dao.CourseDao;
import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.mapper.CourseMapper;
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
    CourseMapper courseMapper;

    @Transactional
    public List<CourseDto> getUserCourses(long userId) {
        return courseDao.getCoursesByUserId(userId).stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(CourseDto courseDto) {
        courseDao.save(courseMapper.toEntity(courseDto));
    }

}
