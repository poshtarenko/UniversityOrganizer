package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public void save(SaveScheduleDto ScheduleDto) {

    }

    @Override
    public void delete(long scheduleId) {

    }

    @Override
    public List<ScheduleItemDto> getScheduleItemsForSemester(long semesterId, long userId) {
        return null;
    }

    @Override
    public List<ScheduleItemDto> getAllUsersScheduleItems(long userId) {
        return null;
    }

    @Override
    public List<ScheduleDto> getScheduleForUserId(long userId) {
        return null;
    }

    @Override
    public Boolean isScheduleBelongToUser(long userId) {
        return null;
    }
}
