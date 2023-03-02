package com.nyanband.university_organizer.validation.annotation;

import com.nyanband.university_organizer.validation.validator.UniqueDisciplineNameValidator;
import com.nyanband.university_organizer.validation.validator.UniqueFileNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFileNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFileNameConstraint {
    String message() default "File with this name is already created";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
