package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="schedule",
        uniqueConstraints = {@UniqueConstraint(columnNames = "semester_id")})
public class Schedule  extends BaseEntity{
    //owning side

    //owning side
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id",referencedColumnName = "id")
    Semester semester;


    @OneToMany(mappedBy = "schedule",fetch = FetchType.LAZY)
    List <ScheduleItem> scheduleItems;

    public Schedule(Long id) {
        super(id);
    }
}
