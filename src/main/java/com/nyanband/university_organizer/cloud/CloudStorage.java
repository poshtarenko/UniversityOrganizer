package com.nyanband.university_organizer.cloud;

import java.io.InputStream;

public interface CloudStorage {

    /**
     * Get image from cloud
     *
     * @param fileID fileID of image
     * @return input stream with image
     */
    InputStream getFile(String fileID);

    /**
     * Deletes any file from cloud by ID
     *
     * @param fileID fileID of image
     */
    void deleteFile(String fileID);

    /**
     * Get image from cloud
     * @return generated fileId
     */
    String saveFile(String fileName, InputStream inputStream, String mimeType);



}
