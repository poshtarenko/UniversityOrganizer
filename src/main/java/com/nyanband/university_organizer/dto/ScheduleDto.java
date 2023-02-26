package com.nyanband.university_organizer.dto;

import lombok.Value;

import java.util.List;

@Value
public class ScheduleDto {
    Long id;
    Long semesterId;
    List<ScheduleItemDto> scheduleItemList;
}
