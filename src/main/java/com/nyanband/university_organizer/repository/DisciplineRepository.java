package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    List<Discipline> getDisciplinesBySemesterId(long semesterId);

    Optional<Discipline> findByNameAndSemesterId(String name, Long semesterId);

    void deleteById(long disciplineId);

    @Query("select count(d) > 0 from Discipline d " +
            "where d.id = :disciplineId and d.semester.course.user.id = :userId")
    Boolean disciplineBelongsToUser(long disciplineId, long userId);
}
