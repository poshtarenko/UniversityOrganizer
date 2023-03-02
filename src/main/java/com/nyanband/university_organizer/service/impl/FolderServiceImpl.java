package com.nyanband.university_organizer.service.impl;

import com.nyanband.university_organizer.dto.FolderDto;
import com.nyanband.university_organizer.dto.SaveFolderDto;
import com.nyanband.university_organizer.dto.mapper.FolderMapper;
import com.nyanband.university_organizer.entity.Discipline;
import com.nyanband.university_organizer.entity.Folder;
import com.nyanband.university_organizer.repository.FolderRepository;
import com.nyanband.university_organizer.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    FolderRepository folderRepository;
    FolderMapper folderMapper;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, FolderMapper folderMapper) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
    }

    @Override
    public List<FolderDto> getDisciplineFolders(long disciplineId) {
        return folderRepository.getFoldersByDisciplineId(disciplineId).stream()
                .map(folderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean isFolderBelongsToUser(long folderId, long userId) {
        return folderRepository.folderBelongsToUser(folderId, userId);
    }

    @Override
    @Transactional
    public FolderDto save(@Valid SaveFolderDto saveFolderDto) {
        Folder folder = folderRepository.save(folderMapper.toEntity(saveFolderDto));
        return folderMapper.toDto(folder);
    }

    @Override
    @Transactional
    public void delete(long folderId) {
        folderRepository.deleteById(folderId);
    }
}
