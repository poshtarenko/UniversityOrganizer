package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> getFoldersByDisciplineId(long disciplineId);

    void deleteById(long disciplineId);

    @Query("select count(f) > 0 from Folder f " +
            "where f.id = :folderId and f.discipline.semester.course.user.id = :userId")
    Boolean folderBelongsToUser(long folderId, long userId);
}
