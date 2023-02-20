package com.nyanband.university_organizer.dao;

import com.nyanband.university_organizer.entity.Course;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CourseDao {

    @PersistenceContext
    private Session session;

    public List<Course> getCoursesByUserId(long userId) {
        return session.createQuery("select c from Course c where c.user.id = :userId", Course.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void save(Course course){
        session.save(course);
    }
}
