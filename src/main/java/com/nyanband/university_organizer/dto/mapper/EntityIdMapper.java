package com.nyanband.university_organizer.dto.mapper;

import com.nyanband.university_organizer.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityIdMapper {

    public User toUser(Long id) {
        return new User(id);
    }

    public Course toCourse(Long id) {
        return new Course(id);
    }

    public Semester toSemester(Long id) {
        return new Semester(id);
    }

    public Discipline toDiscipline(Long id) {
        return new Discipline(id);
    }

    public Folder toFolder(Long id) {
        return new Folder(id);
    }

    public File toFile(Long id) {
        return new File(id);
    }

    public Long toIdEntity(BaseEntity entity) {
        return entity.getId();
    }

}
