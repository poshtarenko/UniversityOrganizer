package com.nyanband.university_organizer.repository;

import com.nyanband.university_organizer.entity.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ScheduleItemRepository extends JpaRepository <ScheduleItem,Long> {
}
