package com.nyanband.university_organizer.controller.error;

import com.nyanband.university_organizer.exception.AccessDeniedException;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<ValidationError.Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError.Violation(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ValidationError validationError = new ValidationError(
                HttpStatus.BAD_REQUEST,
                "Invalid request parameters",
                violations
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validationError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ValidationError handleConstraintValidation(ConstraintViolationException e) {
        List<ValidationError.Violation> violations = e.getConstraintViolations().stream()
                .map(v -> new ValidationError.Violation(v.getPropertyPath().toString(), v.getMessage()))
                .collect(Collectors.toList());

        return new ValidationError(
                HttpStatus.BAD_REQUEST,
                "Invalid request parameters",
                violations
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
