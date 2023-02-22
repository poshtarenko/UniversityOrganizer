package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.SemesterDto;
import com.nyanband.university_organizer.repository.SemesterRepository;
import com.nyanband.university_organizer.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SemesterServiceImpl implements SemesterService {

    SemesterRepository semesterRepository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    @Transactional
    public boolean isSemesterBelongsToUser(long semesterId, long userId) {
        return semesterRepository.semesterBelongsToUser(semesterId, userId);
    }

    @Override
    @Transactional
    public void save(SemesterDto semesterDto) {

    }

    @Override
    @Transactional
    public void delete(long semesterId) {

    }
}
