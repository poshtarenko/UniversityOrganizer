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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(description = "File API")
public class FileController {

    FolderService folderService;
    FileService fileService;

    @Autowired
    public FileController(FolderService folderService, FileService fileService) {
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @GetMapping
    @ApiOperation("Get all files by folder id")
    public List<FileDto> getFolderFiles(@RequestParam Long folderId) {
        long userId = ControllerUtils.getUserId();
        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            return fileService.getFolderFiles(folderId);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

    @PostMapping
    @ApiOperation("Create new file")
    public ResponseEntity<?> addFile(@RequestBody SaveFileDto fileDto) {
        long userId = ControllerUtils.getUserId();
        long folderId = fileDto.getFolderId();
        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            fileService.save(fileDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

    @PostMapping("/delete")
    @ApiOperation("Delete file by id")
    public ResponseEntity<?> deleteFile(@RequestBody Long fileId) {
        long userId = ControllerUtils.getUserId();

        if (fileService.isFileBelongsToUser(fileId, userId)) {
            folderService.delete(fileId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new AccessDeniedException("File does not exist or user dont have access on it");
        }
    }

}
