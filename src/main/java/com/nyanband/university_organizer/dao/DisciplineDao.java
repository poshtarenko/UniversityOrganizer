package com.nyanband.university_organizer.dao;

import com.nyanband.university_organizer.entity.Course;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DisciplineDao {

    @PersistenceContext
    private Session session;

    public List<Course> getCoursesByUserId(long userId) {
        return session.createQuery("select c from Course c where c.user.id = :userId", Course.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void save(Course course) {
        session.save(course);
    }

    public void delete(long courseId) {
        session.createQuery("delete from Course c where c.id = :courseId")
                .setParameter("courseId", courseId)
                .executeUpdate();
    }
}
