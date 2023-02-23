package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name ="UserSetting",
        uniqueConstraints = {@UniqueConstraint(columnNames = "user_id")})
public class UserSettings extends BaseEntity {


    @Column(name = "lesson_time")
    Integer lesson_time;

    @Column(name = "break_time")
    Integer break_time;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    public UserSettings(Long id) {
        super(id);
    }
}
