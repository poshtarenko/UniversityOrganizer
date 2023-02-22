package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    @Query("select count(s) > 0 from Semester s " +
            "where s.id = :semesterId and s.course.user.id = :userId")
    Boolean semesterBelongsToUser(long semesterId, long userId);
}
