package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface DisciplineService {

    List<DisciplineDto> getSemesterDisciplines(long semesterId);

    boolean isDisciplineBelongsToUser(long disciplineId, long userId);

    DisciplineDto save(@Valid SaveDisciplineDto disciplineDto);

    void  delete(long disciplineId);
}
