package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> getCoursesByUserId(long userId);

    void deleteById(long courseId);

    Boolean existsByIdAndUser(long courseId, User user);

}
