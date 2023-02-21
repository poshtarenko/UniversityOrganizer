package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
}
