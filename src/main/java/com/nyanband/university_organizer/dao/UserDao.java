package com.nyanband.university_organizer.dao;

import com.nyanband.university_organizer.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
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
                .uniqueResultOptional().isPresent();
    }

}
