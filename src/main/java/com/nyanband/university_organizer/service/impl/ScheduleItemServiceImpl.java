package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.service.ScheduleItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService {


    @Override
    public void save(SaveScheduleItemDto ScheduleDto) {

    }

    @Override
    public void delete(long scheduleItemId) {

    }

    @Override
    public boolean isScheduleBelongsToUser(long semesterId, long userId) {
        return false;
    }

    @Override
    public List<ScheduleItemDto> getScheduleItemsForSemester(long semesterId, long userId) {
        return null;
    }
}
