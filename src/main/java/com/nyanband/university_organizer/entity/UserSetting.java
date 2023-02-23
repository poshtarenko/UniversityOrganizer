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
@Table(name ="usersetting",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class UserSetting extends BaseEntity {


    @Column(name = "lesson_duration")
    Integer lesson_time;

    @Column(name = "break_time")
    Integer break_time;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public UserSetting(Long id) {
        super(id);
    }
}
