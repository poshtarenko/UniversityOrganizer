package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.DisciplineDto;
import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveDisciplineDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.DisciplineService;
import com.nyanband.university_organizer.service.FolderService;
import com.nyanband.university_organizer.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    DisciplineService disciplineService;
    FolderService folderService;

    @Autowired
    public FolderController(DisciplineService disciplineService, FolderService folderService) {
        this.disciplineService = disciplineService;
        this.folderService = folderService;
    }

    @GetMapping
    public List<FolderDto> getDisciplineFolders(Authentication authentication,
                                                @RequestBody Long disciplineId) {
        long userId = ControllerUtils.getUserId(authentication);
        if (disciplineService.isDisciplineBelongsToUser(disciplineId, userId)) {
            return folderService.getDisciplineFolders(disciplineId);
        } else {
            throw new AccessDeniedException("Semester does not exist or user dont have access on it");
        }
    }

    @PostMapping
    public ResponseEntity<?> addFolder(@RequestBody SaveFolderDto folderDto,
                                           Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        long disciplineId = folderDto.getDisciplineId();
        if (disciplineService.isDisciplineBelongsToUser(disciplineId, userId)) {
            folderService.save(folderDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Discipline does not exist or user dont have access on it");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteDiscipline(Authentication authentication,
                                              @RequestBody Long folderId) {
        long userId = ControllerUtils.getUserId(authentication);

        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            folderService.delete(folderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

}
