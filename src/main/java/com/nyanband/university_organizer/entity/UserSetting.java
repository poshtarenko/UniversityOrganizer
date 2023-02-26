package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="usersetting",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class UserSetting extends BaseEntity {


    @Column(name = "lesson_duration")
    Integer lessonTime;

    @Column(name = "break_time")
    Integer breakTime;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public UserSetting(Long id) {
        super(id);
    }
}
