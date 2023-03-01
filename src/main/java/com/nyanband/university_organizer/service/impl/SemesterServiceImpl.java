package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SemesterDto;
import com.nyanband.university_organizer.dto.mapper.SemesterMapper;
import com.nyanband.university_organizer.repository.SemesterRepository;
import com.nyanband.university_organizer.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class SemesterServiceImpl implements SemesterService {

    SemesterRepository semesterRepository;
    SemesterMapper semesterMapper;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository, SemesterMapper semesterMapper) {
        this.semesterRepository = semesterRepository;
        this.semesterMapper = semesterMapper;
    }

    @Override
    @Transactional
    public boolean isSemesterBelongsToUser(long semesterId, long userId) {
        return semesterRepository.semesterBelongsToUser(semesterId, userId);
    }

    @Override
    @Transactional
    public SemesterDto save(@Valid SemesterDto semesterDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long semesterId) {
        semesterRepository.deleteById(semesterId);
    }
}
