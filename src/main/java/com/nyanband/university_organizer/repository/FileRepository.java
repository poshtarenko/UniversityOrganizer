package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> getFilesByFolderId(long folderId);

    Optional<File> findByNameAndFolderId(String name, Long folderId);

    void deleteById(long fileId);

    @Query("select count(f) > 0 from File f " +
            "where f.id = :fileId and f.folder.discipline.semester.course.user.id = :userId")
    Boolean fileBelongsToUser(long fileId, long userId);
}
