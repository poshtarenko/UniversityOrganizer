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
@Table(name = "folder",
        uniqueConstraints = @UniqueConstraint(columnNames = {"discipline_id", "name"}))
public class Folder extends BaseEntity {
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline_id")
    Discipline discipline;

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    List<File> files;

    public Folder(Long id) {
        super(id);
    }
}
