package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;

import java.util.List;

public interface ScheduleItemService {

    void save(SaveScheduleItemDto saveScheduleItemDto);
    void delete(long scheduleItemId);

//    boolean isScheduleBelongsToUser(long semesterId, long userId);
    void update(SaveScheduleItemDto saveScheduleItemDto);
    List<ScheduleItemDto> getScheduleItemsForSemester(long semesterId, long userId);

    List<ScheduleItemDto> getScheduleItemsForSchedule(long scheduleId, long userId);
}
