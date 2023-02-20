package com.nyanband.university_organizer.dao;

import com.nyanband.university_organizer.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class UserDao {

    @PersistenceContext
    private Session session;

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(
                session.createQuery("select u from User u where u.email = :email", User.class)
                        .setParameter("email", email)
                        .getSingleResult());
    }

    public Boolean existsByEmail(String email) {
        return session.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult() != null;
    }

}
