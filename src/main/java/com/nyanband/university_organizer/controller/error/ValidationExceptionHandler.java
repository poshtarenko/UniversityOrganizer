package com.nyanband.university_organizer.controller.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ApiIgnore
class ValidationExceptionHandler {

//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    ValidationError onConstraintValidationException(ConstraintViolationException e) {
//        List<ValidationError.Violation> violations = e.getConstraintViolations().stream()
//                .map(v -> new ValidationError.Violation(v.getPropertyPath().toString(), v.getMessage()))
//                .collect(Collectors.toList());
//
//        return new ValidationError(
//                HttpStatus.BAD_REQUEST,
//                "Invalid request parameters",
//                violations
//        );
//    }
//
}