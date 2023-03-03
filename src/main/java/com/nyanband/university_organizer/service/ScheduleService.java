package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;

import java.util.List;

public interface ScheduleService {


    void save(SaveScheduleDto ScheduleDto);

    void delete(long scheduleId);


    List<ScheduleDto> getScheduleForUserId(long userId);

    Boolean isScheduleExist(long scheduleId);
    Boolean isScheduleBelongToUser(long userId,long scheduleId);

    boolean isSemesterHasSchedule(long semesterId);
}
