package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.dto.SemesterDto;

import java.util.List;

public interface FolderService {

    List<FolderDto> getDisciplineFolders(long disciplineId);

    boolean isFolderBelongsToUser(long folderId, long userId);

    FolderDto save(SaveFolderDto semesterDto);

    void delete(long folderId);
}
