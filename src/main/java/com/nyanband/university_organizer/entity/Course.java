package com.nyanband.university_organizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "num")
    Integer number;
    //List<Semester> semesters;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
