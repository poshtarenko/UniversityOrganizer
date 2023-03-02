package com.nyanband.university_organizer.validation.annotation;

import com.nyanband.university_organizer.validation.validator.UniqueDisciplineNameValidator;
import com.nyanband.university_organizer.validation.validator.UniqueFolderNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFolderNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFolderNameConstraint {
    String message() default "Folder with this name is already created";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
