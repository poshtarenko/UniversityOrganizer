package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.dto.SemesterDto;

import java.util.List;

public interface ScheduleService {

    //boolean isScheduleBelongsToUser(long semesterId, long userId);

    void save(SaveScheduleDto ScheduleDto);

    void delete(long scheduleId);


    List<ScheduleItemDto> getScheduleItemsForSemester(long semesterId, long userId);

    List<ScheduleItemDto> getAllUsersScheduleItems(long userId);
    List <ScheduleDto>   getScheduleForUserId(long userId);


    Boolean isScheduleBelongToUser(long userId);
}
