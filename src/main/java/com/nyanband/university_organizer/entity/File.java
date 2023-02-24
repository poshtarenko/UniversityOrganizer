package com.nyanband.university_organizer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class File extends BaseEntity {
    String name;

    String path;

    @Column(name = "upload_time")
    LocalDateTime uploadTime;

    @Column(name = "mime_type")
    String mimeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "folder_id")
    Folder folder;

    public File(Long id) {
        super(id);
    }
}
