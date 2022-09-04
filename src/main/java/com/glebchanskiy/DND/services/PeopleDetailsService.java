package com.glebchanskiy.DND.services;

import com.glebchanskiy.DND.models.Person;
import com.glebchanskiy.DND.repositories.PeopleRepository;
import com.glebchanskiy.DND.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = peopleRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new PersonDetails(user.get());
    }

}
