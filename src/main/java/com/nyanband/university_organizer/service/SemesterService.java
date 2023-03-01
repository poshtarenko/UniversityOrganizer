package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SemesterDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface SemesterService {

    boolean isSemesterBelongsToUser(long semesterId, long userId);

    SemesterDto save(@Valid SemesterDto semesterDto);

    void delete(long semesterId);
}
