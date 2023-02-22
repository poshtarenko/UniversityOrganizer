package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;

import java.util.List;

public interface FileService {

    List<FileDto> getFolderFiles(long folderId);

    boolean isFileBelongsToUser(long fileId, long userId);

    void save(SaveFileDto fileDto);

    void delete(long fileId);
}
