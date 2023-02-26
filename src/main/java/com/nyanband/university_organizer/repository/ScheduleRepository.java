package com.nyanband.university_organizer.repository;


import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
    @Query("SELECT s  FROM Schedule s  WHERE  s.semester.course.user.id =:userId")
    List<Schedule> getSchedulesByUserId(long userId);


}
