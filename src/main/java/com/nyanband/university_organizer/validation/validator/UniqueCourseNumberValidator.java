package com.nyanband.university_organizer.validation.validator;

import com.nyanband.university_organizer.repository.CourseRepository;
import com.nyanband.university_organizer.validation.annotation.UniqueCourseNumberConstraint;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCourseNumberValidator
        implements ConstraintValidator<UniqueCourseNumberConstraint, Object> {

    CourseRepository courseRepository;

    @Autowired
    public UniqueCourseNumberValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void initialize() {
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        BeanWrapperImpl courseBeanWrapper = new BeanWrapperImpl(value);
        Integer courseNumber = (Integer) courseBeanWrapper.getPropertyValue("number");
        Long userId = (Long) courseBeanWrapper.getPropertyValue("userId");

        return courseRepository.findByNumberAndUserId(courseNumber, userId).isEmpty();
    }
}
