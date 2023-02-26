package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.ScheduleDto;

public interface ScheduleItemService {

    void save(ScheduleDto ScheduleDto);

    void delete(long scheduleId);
    //boolean isScheduleBelongsToUser(long semesterId, long userId);
}
