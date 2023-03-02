package com.nyanband.university_organizer.dto;

import com.nyanband.university_organizer.validation.annotation.UniqueFileNameConstraint;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.InputStream;

@Value
@UniqueFileNameConstraint
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
