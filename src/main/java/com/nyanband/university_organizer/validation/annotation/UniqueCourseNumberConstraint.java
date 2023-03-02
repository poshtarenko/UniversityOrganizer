package com.nyanband.university_organizer.validation.annotation;

import com.nyanband.university_organizer.validation.validator.UniqueCourseNumberValidator;
import com.nyanband.university_organizer.validation.validator.UniqueDisciplineNameValidator;
import com.nyanband.university_organizer.validation.validator.UniqueUserEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCourseNumberValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCourseNumberConstraint {
    String message() default "Course with this number is already created";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
