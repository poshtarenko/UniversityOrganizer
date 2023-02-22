package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.entity.File;
import com.nyanband.university_organizer.entity.Folder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {EntityIdMapper.class})
public interface FileMapper {

    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    FileDto toDto(File file);

    @Mapping(source = "folderId", target = "folder")
    File toEntity(SaveFileDto fileDto);

}
