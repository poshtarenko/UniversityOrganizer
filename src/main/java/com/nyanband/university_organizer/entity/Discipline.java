package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Discipline extends BaseEntity {
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id")
    Semester semester;

    @OneToMany(mappedBy = "discipline", fetch = FetchType.LAZY)
    List<Folder> folders;

//    @OneToMany(mappedBy = "discipline",fetch =  FetchType.LAZY)
//    List <ScheduleItem> scheduleItems;

    public Discipline(Long id) {
        super(id);
    }
}