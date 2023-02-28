package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public interface FileService {

    List<FileDto> getFolderFiles(long folderId);

    FileContentDto getFileContent(long fileId);

    boolean isFileBelongsToUser(long fileId, long userId);

    FileDto save(SaveFileDto fileDto);

    void delete(long fileId);
}
