package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;

import java.util.List;

public interface DisciplineService {

    List<DisciplineDto> getSemesterDisciplines(long semesterId);

    boolean isDisciplineBelongsToUser(long disciplineId, long userId);

    DisciplineDto save(SaveDisciplineDto disciplineDto);

    void  delete(long disciplineId);
}
