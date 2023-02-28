package com.nyanband.university_organizer.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.InputStream;

@Value
public class SaveFileDto {
    @NotEmpty(message = "Name can not be empty")
    String name;

    @Positive(message = "Folder id must be positive")
    Long folderId;

    @NotNull(message = "File content can not be null")
    InputStream fileContent;

    @NotEmpty(message = "Mime type can not be empty")
    String mimeType;
}
