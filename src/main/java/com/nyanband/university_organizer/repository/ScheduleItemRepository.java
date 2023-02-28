package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ScheduleItemRepository extends JpaRepository <ScheduleItem,Long> {
    void deleteById(long scheduleItemId);

    Boolean scheduleItemBelongsToSchedule(long disciplineId, long userId);

    List<ScheduleItem> getScheduleItemsByScheduleId(long scheduleId);
}
