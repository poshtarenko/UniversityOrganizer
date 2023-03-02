package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.dto.mapper.ScheduleMapper;
import com.nyanband.university_organizer.entity.Schedule;
import com.nyanband.university_organizer.entity.Semester;
import com.nyanband.university_organizer.repository.ScheduleRepository;
import com.nyanband.university_organizer.repository.SemesterRepository;
import com.nyanband.university_organizer.service.ScheduleItemService;
import com.nyanband.university_organizer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;
    SemesterRepository semesterRepository;
    ScheduleItemService scheduleItemService;
    ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                               SemesterRepository semesterRepository,
                               ScheduleItemService scheduleItemService,
                               ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.semesterRepository = semesterRepository;
        this.scheduleItemService = scheduleItemService;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    @Transactional
    public void save(SaveScheduleDto saveScheduleDto) {
        scheduleRepository.save(scheduleMapper.toEntity(saveScheduleDto));
    }

    @Override
    public Boolean isScheduleExist(long scheduleId) {
        return scheduleRepository.isScheduleExist(scheduleId);
    }

    @Override
    @Transactional
    public void delete(long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    @Override
    @Transactional
    public List<ScheduleDto> getScheduleForUserId(long userId) {
        return scheduleRepository.getSchedulesByUserId(userId).stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSemesterHasSchedule(long semesterId) {
        return scheduleRepository.isSemesterHasSchedule(semesterId);
    }

    @Override
    public Boolean isScheduleBelongToUser(long userId,long scheduleId) {
        return scheduleRepository.isScheduleBelongsToUser(userId,scheduleId);
    }
}
