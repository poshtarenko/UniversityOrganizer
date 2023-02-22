package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.DisciplineService;
import com.nyanband.university_organizer.service.FileService;
import com.nyanband.university_organizer.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    FolderService folderService;
    FileService fileService;

    @Autowired
    public FileController(FolderService folderService, FileService fileService) {
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @GetMapping
    public List<FileDto> getFolderFiles(Authentication authentication,
                                        @RequestBody Long folderId) {
        long userId = ControllerUtils.getUserId(authentication);
        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            return fileService.getFolderFiles(folderId);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

    @PostMapping
    public ResponseEntity<?> addFile(@RequestBody SaveFileDto fileDto,
                                           Authentication authentication) {
        long userId = ControllerUtils.getUserId(authentication);
        long folderId = fileDto.getFolderId();
        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            fileService.save(fileDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFile(Authentication authentication,
                                              @RequestBody Long fileId) {
        long userId = ControllerUtils.getUserId(authentication);

        if (fileService.isFileBelongsToUser(fileId, userId)) {
            folderService.delete(fileId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("File does not exist or user dont have access on it");
        }
    }

}
