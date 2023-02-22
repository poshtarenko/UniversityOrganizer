package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SemesterDto;

public interface SemesterService {

    boolean isSemesterBelongsToUser(long semesterId, long userId);

    void save(SemesterDto folderDto);

    void delete(long semesterId);
}
