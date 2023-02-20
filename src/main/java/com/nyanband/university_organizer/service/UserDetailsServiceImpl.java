package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dao.UserDao;
import com.nyanband.university_organizer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + username + " is unregistered"));

        return UserDetailsImpl.build(user);
    }
}
