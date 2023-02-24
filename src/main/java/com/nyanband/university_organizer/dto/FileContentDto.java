package com.nyanband.university_organizer.dto;

import lombok.Value;

import java.io.InputStream;

@Value
public class FileContentDto {
    String name;
    InputStream fileContent;
    String mimeType;
}
