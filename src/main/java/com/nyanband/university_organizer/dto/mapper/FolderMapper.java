package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.dto.SemesterDto;
import com.nyanband.university_organizer.entity.Folder;
import com.nyanband.university_organizer.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface FolderMapper {

    FolderMapper INSTANCE = Mappers.getMapper(FolderMapper.class);

    FolderDto toDto(Folder folder);

    @Mapping(source = "disciplineId", target = "discipline")
    Folder toEntity(SaveFolderDto folderDto);

}
