package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.File;
import com.nyanband.university_organizer.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> getFilesByFolderId(long folderId);

    void deleteById(long fileId);

    @Query("select count(f) > 0 from File f " +
            "where f.id = :fileId and f.folder.discipline.semester.course.user.id = :userId")
    Boolean fileBelongsToUser(long fileId, long userId);
}
