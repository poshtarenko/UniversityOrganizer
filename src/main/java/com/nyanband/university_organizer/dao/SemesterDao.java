package com.nyanband.university_organizer.dao;

import com.nyanband.university_organizer.entity.Semester;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;

@Repository
public class SemesterDao {

    @PersistenceContext
    private Session session;

    public void save(Semester semester) {
        session.save(semester);
    }


}
