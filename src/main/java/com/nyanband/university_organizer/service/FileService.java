package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@Validated
public interface FileService {

    List<FileDto> getFolderFiles(long folderId);

    FileContentDto getFileContent(long fileId);

    boolean isFileBelongsToUser(long fileId, long userId);

    FileDto save(@Valid SaveFileDto fileDto);

    void delete(long fileId);
}
