package com.nyanband.university_organizer.entity;

import com.nyanband.university_organizer.entity.enums.ELessonType;
import com.nyanband.university_organizer.entity.enums.EWeakType;
import com.nyanband.university_organizer.entity.enums.EWeekday;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "scheduleitem")
@Entity
public class ScheduleItem extends BaseEntity {
    @Column(name = "lesson_num")
    Integer lesson_num;

    @Column(name = "weekday")
    @Enumerated(EnumType.STRING)
    EWeekday weekday;

    @Column(name = "lesson_type")
    @Enumerated(EnumType.STRING)
    ELessonType lessonType;

    @Column(name = "week_type")
    @Enumerated(EnumType.STRING)
    EWeakType weakType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    Discipline discipline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    Schedule schedule;


    public ScheduleItem(Long id) {
        super(id);
    }
}
