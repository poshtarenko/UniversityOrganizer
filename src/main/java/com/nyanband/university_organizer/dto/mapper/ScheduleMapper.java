package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.SaveScheduleDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class, ScheduleItemMapper.class})
public interface ScheduleMapper {

    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);

    @Mapping(source = "semesterId", target = "semester")
    Schedule toEntity(SaveScheduleDto scheduleDto);

    @Mapping(source = "semester", target = "semesterId")
    ScheduleDto toDto(Schedule schedule);

}
