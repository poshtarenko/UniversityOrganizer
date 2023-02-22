package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.DisciplineService;
import com.nyanband.university_organizer.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {

    SemesterService semesterService;
    DisciplineService disciplineService;

    @Autowired
    public DisciplineController(SemesterService semesterService, DisciplineService disciplineService) {
        this.semesterService = semesterService;
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<DisciplineDto> getSemesterDisciplines(Authentication authentication,
                                                @RequestBody Long semesterId) {
        long userId = ControllerUtils.getUserId(authentication);
        if (semesterService.isSemesterBelongsToUser(semesterId, userId)) {
            return disciplineService.getSemesterDisciplines(semesterId);
        } else {
            throw new AccessDeniedException("Semester does not exist or user dont have access on it");
        }
    }

    @PostMapping
    public ResponseEntity<?> addDiscipline(@RequestBody SaveDisciplineDto disciplineDto,
                                           Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        long semesterId = disciplineDto.getSemesterId();
        if (semesterService.isSemesterBelongsToUser(semesterId, userId)) {
            disciplineService.save(disciplineDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Semester does not exist or user dont have access on it");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteDiscipline(Authentication authentication,
                                              @RequestBody Long disciplineId) {
        long userId = ControllerUtils.getUserId(authentication);

        if (disciplineService.isDisciplineBelongsToUser(disciplineId, userId)) {
            disciplineService.delete(disciplineId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Discipline does not exist or user dont have access on it");
        }
    }

}
