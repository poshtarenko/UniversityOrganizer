package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;
import com.nyanband.university_organizer.entity.Discipline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class, SemesterMapper.class})
public interface DisciplineMapper {
    DisciplineMapper INSTANCE = Mappers.getMapper(DisciplineMapper.class);

    @Mapping(source = "semester", target = "semesterId")
    DisciplineDto toDto(Discipline discipline);

    @Mapping(source = "semesterId", target = "semester")
    Discipline toEntity(SaveDisciplineDto disciplineDto);
}
