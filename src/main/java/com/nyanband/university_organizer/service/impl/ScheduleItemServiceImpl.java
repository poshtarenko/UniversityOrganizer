package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.dto.mapper.ScheduleItemMapper;
import com.nyanband.university_organizer.repository.ScheduleItemRepository;
import com.nyanband.university_organizer.service.ScheduleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleItemServiceImpl implements ScheduleItemService {
    ScheduleItemMapper scheduleItemMapper;
    ScheduleItemRepository scheduleItemRepository;



    @Autowired
    public ScheduleItemServiceImpl(ScheduleItemRepository scheduleItemRepository,ScheduleItemMapper scheduleItemMapper) {
        this.scheduleItemMapper = scheduleItemMapper;
        this.scheduleItemRepository = scheduleItemRepository;

    }

    @Override
    public void save(SaveScheduleItemDto saveScheduleItemDto) {
//        if(disciplineExist(saveScheduleItemDto.getDisciplineId())){
             scheduleItemRepository.save(scheduleItemMapper.toEntity(saveScheduleItemDto));
//        }
        //Discipline discipline = new Discipline(saveScheduleItemDto)
    }

    @Override
    public void delete(long scheduleItemId) {
            scheduleItemRepository.delete(scheduleItemRepository.getScheduleItemById(scheduleItemId));
    }

    @Override
    public void update(SaveScheduleItemDto saveScheduleItemDto) {

    }

    @Override
    public List<ScheduleItemDto> getScheduleItemsForSemester(long semesterId, long userId) {
        return null;
    }

//    @Override
//    public boolean isScheduleBelongsToUser(long semesterId, long userId) {
//        return scheduleItemRepository.;
//    }

    @Override
    public List<ScheduleItemDto> getScheduleItemsForSchedule(long scheduleId, long userId) {
        return scheduleItemRepository.getScheduleItemsByScheduleId(scheduleId,userId).stream()
                .map(scheduleItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
