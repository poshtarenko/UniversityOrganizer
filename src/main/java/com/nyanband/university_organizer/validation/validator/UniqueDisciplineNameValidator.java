package com.nyanband.university_organizer.validation.validator;

import com.nyanband.university_organizer.repository.CourseRepository;
import com.nyanband.university_organizer.repository.DisciplineRepository;
import com.nyanband.university_organizer.validation.annotation.UniqueCourseNumberConstraint;
import com.nyanband.university_organizer.validation.annotation.UniqueDisciplineNameConstraint;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDisciplineNameValidator
        implements ConstraintValidator<UniqueDisciplineNameConstraint, Object> {

    DisciplineRepository disciplineRepository;

    @Autowired
    public UniqueDisciplineNameValidator(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public void initialize() {
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        BeanWrapperImpl disciplineBeanWrapper = new BeanWrapperImpl(value);
        String disciplineName = (String) disciplineBeanWrapper.getPropertyValue("name");
        Long semesterId = (Long) disciplineBeanWrapper.getPropertyValue("semesterId");

        return disciplineRepository.findByNameAndSemesterId(disciplineName, semesterId).isEmpty();
    }
}
