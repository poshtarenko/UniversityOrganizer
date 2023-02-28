package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.CourseDto;
import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;
import com.nyanband.university_organizer.dto.mapper.CourseMapper;
import com.nyanband.university_organizer.dto.mapper.DisciplineMapper;
import com.nyanband.university_organizer.entity.Course;
import com.nyanband.university_organizer.entity.Discipline;
import com.nyanband.university_organizer.entity.Folder;
import com.nyanband.university_organizer.entity.Semester;
import com.nyanband.university_organizer.repository.CourseRepository;
import com.nyanband.university_organizer.repository.DisciplineRepository;
import com.nyanband.university_organizer.repository.SemesterRepository;
import com.nyanband.university_organizer.service.CourseService;
import com.nyanband.university_organizer.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    DisciplineRepository disciplineRepository;
    DisciplineMapper disciplineMapper;

    @Autowired
    public DisciplineServiceImpl(DisciplineRepository disciplineRepository,
                                 DisciplineMapper disciplineMapper) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
    }

    @Override
    @Transactional
    public List<DisciplineDto> getSemesterDisciplines(long semesterId) {
        return disciplineRepository.getDisciplinesBySemesterId(semesterId).stream()
                .map(disciplineMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean isDisciplineBelongsToUser(long disciplineId, long userId) {
        return disciplineRepository.disciplineBelongsToUser(disciplineId, userId);
    }

    @Override
    @Transactional
    public DisciplineDto save(@Valid SaveDisciplineDto saveDisciplineDto) {
        Discipline discipline = disciplineMapper.toEntity(saveDisciplineDto);
        DisciplineDto disciplineDto = disciplineMapper.toDto(discipline);
        disciplineRepository.save(discipline);
        return  disciplineDto;
    }

    @Override
    @Transactional
    public void delete(long disciplineId) {
        disciplineRepository.deleteById(disciplineId);
    }

}
