package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> getCoursesByUserId(long userId);
}
