package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.SaveScheduleItemDto;
import com.nyanband.university_organizer.dto.ScheduleItemDto;
import com.nyanband.university_organizer.entity.Discipline;
import com.nyanband.university_organizer.entity.ScheduleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class, ScheduleMapper.class, DisciplineMapper.class})
public interface ScheduleItemMapper {
    ScheduleItemMapper INSTANCE = Mappers.getMapper(ScheduleItemMapper.class);

    @Mapping(source = "disciplineId", target = "discipline")
    @Mapping(source = "scheduleId", target =  "schedule")
    ScheduleItem toEntity(SaveScheduleItemDto scheduleItemDto);

    @Mapping(source = "discipline", target = "disciplineId")
    @Mapping(source = "schedule", target =  "scheduleId")
    ScheduleItemDto toDto(ScheduleItem scheduleItem);


}
