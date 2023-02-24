package com.nyanband.university_organizer.cloud;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class CloudStorageImpl implements CloudStorage {

    private final Drive cloudService;

    public CloudStorageImpl(Drive cloudService) {
        this.cloudService = cloudService;
    }

    @Override
    @SneakyThrows
    public InputStream getFile(String fileID) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        cloudService.files().get(fileID)
                .executeMediaAndDownloadTo(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public void deleteFile(String fileID) {
        try {
            cloudService.files().delete(fileID).execute();
        } catch (IOException e) {
//            log.warn("File deleting failed, fileID={}", fileID);
        }
    }

    public String saveFile(String fileName, InputStream inputStream, String mimeType) {
        File file = new File();
        file.setName(fileName);

        InputStreamContent content = new InputStreamContent(mimeType, inputStream);

        try {
            File fileToUpload = cloudService.files()
                    .create(file, content)
                    .setFields("id")
                    .execute();
            return fileToUpload.getId();
        } catch (IOException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }


}
