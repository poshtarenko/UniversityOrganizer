package com.nyanband.university_organizer.exception.handler;

import com.nyanband.university_organizer.exception.AccessDeniedException;
import com.nyanband.university_organizer.exception.error.ApiError;
import com.nyanband.university_organizer.exception.error.ValidationError;
import com.nyanband.university_organizer.exception.util.ExceptionHandlerUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@ApiIgnore
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return ExceptionHandlerUtils.buildResponseEntity(
                new ApiError(
                        HttpStatus.BAD_REQUEST,
                        "Malformed JSON request",
                        ex
                )
        );
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<ValidationError.Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError.Violation(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

        return ExceptionHandlerUtils.buildResponseEntity(
                new ValidationError(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Invalid request parameters",
                        violations
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleConstraintValidation(ConstraintViolationException e) {
        List<ValidationError.Violation> violations = e.getConstraintViolations().stream()
                .map(v -> new ValidationError.Violation(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                ))
                .collect(Collectors.toList());

        return ExceptionHandlerUtils.buildResponseEntity(
                new ValidationError(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Invalid request parameters",
                        violations
                )
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        return ExceptionHandlerUtils.buildResponseEntity(
                new ApiError(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        ex
                )
        );
    }

}
