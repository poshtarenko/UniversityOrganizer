package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.FileContentDto;
import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface FileService {

    List<FileDto> getFolderFiles(long folderId);

    FileContentDto getFileContent(long fileId);

    boolean isFileBelongsToUser(long fileId, long userId);

    FileDto save(@Valid SaveFileDto fileDto);

    void delete(long fileId);
}
