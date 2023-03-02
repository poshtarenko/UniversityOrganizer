package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {
    void deleteById(long scheduleItemId);

    //select count(us) > 0  from UserSetting  us  where  us.id = :settingId and us.user.id = :userId
    @Query("SELECT COUNT(si) > 0 FROM ScheduleItem si where si.schedule.id =:scheduleId  and si.discipline.id =:disciplineId")
    Boolean scheduleItemBelongsToSchedule(long disciplineId, long scheduleId);
    @Query ("Select si FROM ScheduleItem si where si.schedule.id =:scheduleId and si.schedule.semester.course.user.id =:userId")
    List<ScheduleItem> getScheduleItemsByScheduleId(long scheduleId,long userId);

//    List<ScheduleItem> getScheduleItemsByScheduleId(long scheduleId);

    ScheduleItem getScheduleItemById(long scheduleItemId);
}
