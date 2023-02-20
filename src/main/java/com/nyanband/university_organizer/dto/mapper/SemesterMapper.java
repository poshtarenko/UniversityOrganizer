package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.SemesterDto;
import com.nyanband.university_organizer.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

    SemesterMapper INSTANCE = Mappers.getMapper(SemesterMapper.class);

    SemesterDto toDto(Semester semester);

    Semester toEntity(SemesterDto semesterDto);

}
