package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.cloud.CloudStorage;
import com.nyanband.university_organizer.cloud.CloudStorageImpl;
import com.nyanband.university_organizer.config.GoogleDriveConfig;
import com.nyanband.university_organizer.dto.FileContentDto;
import com.nyanband.university_organizer.dto.FileDto;
import com.nyanband.university_organizer.dto.SaveFileDto;
import com.nyanband.university_organizer.dto.mapper.FileMapper;
import com.nyanband.university_organizer.entity.File;
import com.nyanband.university_organizer.repository.FileRepository;
import com.nyanband.university_organizer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    FileRepository fileRepository;
    FileMapper fileMapper;
    CloudStorage cloudStorage;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper, CloudStorage cloudStorage) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.cloudStorage = cloudStorage;
    }

    @Override
    @Transactional
    public List<FileDto> getFolderFiles(long folderId) {
        return fileRepository.getFilesByFolderId(folderId).stream()
                .map(fileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FileContentDto getFileContent(long fileId) {
        File file = fileRepository.findById(fileId).orElseThrow(RuntimeException::new);

        return new FileContentDto(
                file.getName(),
                cloudStorage.getFile(file.getPath()),
                file.getMimeType()
        );
    }

    @Override
    @Transactional
    public boolean isFileBelongsToUser(long fileId, long userId) {
        return fileRepository.fileBelongsToUser(fileId, userId);
    }

    @Override
    @Transactional
    public void save(SaveFileDto fileDto) {
        String filePath = cloudStorage.saveFile(
                fileDto.getName(),
                fileDto.getFileContent(),
                fileDto.getMimeType()
        );

        File file = fileMapper.toEntity(fileDto);
        file.setUploadTime(LocalDateTime.now());
        file.setPath(filePath);
        fileRepository.save(file);
    }

    @Override
    @Transactional
    public void delete(long fileId) {
        fileRepository.deleteById(fileId);
    }
}
