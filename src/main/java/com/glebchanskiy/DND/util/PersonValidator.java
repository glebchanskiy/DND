package com.glebchanskiy.DND.util;

import com.glebchanskiy.DND.models.Person;
import com.glebchanskiy.DND.services.PeopleDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleDetailsService peopleDetailsService;

    @Autowired
    public PersonValidator(PeopleDetailsService peopleDetailsService) {
        this.peopleDetailsService = peopleDetailsService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        try {
            peopleDetailsService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "username in use");
    }
}
