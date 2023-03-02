package com.nyanband.university_organizer.validation.validator;

import com.nyanband.university_organizer.repository.UserRepository;
import com.nyanband.university_organizer.validation.annotation.UniqueUserEmailConstraint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements
        ConstraintValidator<UniqueUserEmailConstraint, String> {

    UserRepository userRepository;

    @Autowired
    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserEmailConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext cxt) {
        return userRepository.findByEmail(email).isEmpty();
    }

}
