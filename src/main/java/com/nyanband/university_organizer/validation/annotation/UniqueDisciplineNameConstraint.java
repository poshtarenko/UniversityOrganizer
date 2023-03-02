package com.nyanband.university_organizer.validation.annotation;

import com.nyanband.university_organizer.validation.validator.UniqueCourseNumberValidator;
import com.nyanband.university_organizer.validation.validator.UniqueDisciplineNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueDisciplineNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDisciplineNameConstraint {
    String message() default "Discipline with this name is already created";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
