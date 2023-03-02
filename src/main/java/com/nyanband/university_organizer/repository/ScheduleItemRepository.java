package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {
    void deleteById(long scheduleItemId);

    @Query("SELECT COUNT(si) > 0 FROM ScheduleItem si where si.id =:scheduleItemId  and si.schedule.semester.course.user.id =:userId")
    Boolean scheduleItemBelongsToSchedule(long userId, long scheduleItemId);
    @Query ("Select si FROM ScheduleItem si where si.schedule.id =:scheduleId and si.schedule.semester.course.user.id =:userId")
    List<ScheduleItem> getScheduleItemsByScheduleId(long scheduleId,long userId);




}
