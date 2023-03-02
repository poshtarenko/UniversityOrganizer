package com.nyanband.university_organizer.controller;

import com.nyanband.university_organizer.controller.util.ControllerUtils;
import com.nyanband.university_organizer.dto.FileContentDto;
import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.service.FileService;
import com.nyanband.university_organizer.service.FolderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    public List<FileDto> getFolderFiles(@RequestParam("folderId") Long folderId) {
        long userId = ControllerUtils.getUserId();
        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            return fileService.getFolderFiles(folderId);
        } else {
            throw new AccessDeniedException("Folder does not exist or user dont have access on it");
        }
    }

    @GetMapping("/{fileId}")
    @ApiOperation("Get all files by folder id")
    public ResponseEntity<InputStreamResource> getFileContent(@PathVariable Long fileId) {
        long userId = ControllerUtils.getUserId();
        if (fileService.isFileBelongsToUser(fileId, userId)) {
            FileContentDto fileContent = fileService.getFileContent(fileId);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileContent.getName())
                    .contentType(MediaType.parseMediaType(fileContent.getMimeType()))
                    .body(new InputStreamResource(fileContent.getFileContent()));
        } else {
            throw new AccessDeniedException("File does not exist or user dont have access on it");
        }
    }

    @PostMapping
    @ApiOperation("Create new file")
    public FileDto addFile(@RequestParam("folderId") Long folderId,
                           @RequestPart("file") MultipartFile file) {
        FileDto fileDto;
        long userId = ControllerUtils.getUserId();

        if (folderService.isFolderBelongsToUser(folderId, userId)) {
            try {
                InputStream fileContent = file.getInputStream();
                Tika tika = new Tika();
                String mimeType = tika.detect(file.getInputStream());

                SaveFileDto saveFileDto = new SaveFileDto(
                        file.getOriginalFilename(),
                        folderId,
                        fileContent,
                        mimeType
                );

                fileDto = fileService.save(saveFileDto);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return fileDto;
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
            return ControllerUtils.okResponse();
        } else {
            throw new AccessDeniedException("File does not exist or user dont have access on it");
        }
    }

}
