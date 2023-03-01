package com.nyanband.university_organizer.repository;


import com.nyanband.university_organizer.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s  FROM Schedule s  WHERE  s.semester.course.user.id =:userId")
    List<Schedule> getSchedulesByUserId(long userId);

    @Query("SELECT COUNT(s) > 0  FROM Schedule  s WHERE s.id =:scheduleId and s.semester.course.user.id =:userId")
    Boolean isScheduleBelongsToUser(long userId, long scheduleId);


}
