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
public class ScheduleItem extends BaseEntity{

    Integer lesson_num;
    @Enumerated(EnumType.STRING)
    EWeekday weekday;
    @Enumerated(EnumType.STRING)
    ELessonType lessonType;
    @Enumerated(EnumType.STRING)
    EWeakType weakType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id",referencedColumnName = "id")
    Discipline discipline;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    Schedule schedule;





    public ScheduleItem(Long id) {
        super(id);
    }
}
