package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> getCoursesByUserId(long userId);

    void deleteById(long courseId);



    @Query("select count(c) > 0 from Course c where c.id = :courseId and c.user.id = :userId")
    Boolean courseBelongsToUser(long courseId, long userId);

}
