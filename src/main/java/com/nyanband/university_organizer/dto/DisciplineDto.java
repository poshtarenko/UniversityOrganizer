package com.nyanband.university_organizer.dto;

import lombok.Value;

import java.util.List;

@Value
public class DisciplineDto {
    Long id;
    String name;
    Long semesterId;
    List<FolderDto> folders;
}
