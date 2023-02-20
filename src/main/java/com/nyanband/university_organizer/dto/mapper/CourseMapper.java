package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BaseEntityIdMapper.class})
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "user", target = "userId")
    CourseDto toDto(Course course);

    @Mapping(source = "userId", target = "user")
    Course toEntity(CourseDto courseDto);

}
