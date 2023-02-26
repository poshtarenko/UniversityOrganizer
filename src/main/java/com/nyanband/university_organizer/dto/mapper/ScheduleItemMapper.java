package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.entity.ScheduleItem;
import org.mapstruct.Mapper;

@Mapper
public interface ScheduleItemMapper {

    ScheduleItem toEntity(SaveScheduleItemDto scheduleItemDto);
    ScheduleItemDto toDto(ScheduleItem scheduleItem);

}
