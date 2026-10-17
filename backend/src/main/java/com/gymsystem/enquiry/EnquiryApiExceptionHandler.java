package com.gymsystem.enquiry;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class EnquiryApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalidFields(MethodArgumentNotValidException exception) {
        Map<String, String> fieldErrors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .body(new ApiError("VALIDATION_ERROR", "Check the highlighted fields.", fieldErrors));
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ApiError> invalidPhone() {
        return ResponseEntity.badRequest().body(new ApiError("VALIDATION_ERROR",
                "Check the highlighted fields.", Map.of("phone", "Enter a valid 10-digit Indian mobile number.")));
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ApiError> invalidName() {
        return ResponseEntity.badRequest().body(new ApiError("VALIDATION_ERROR",
                "Check the highlighted fields.", Map.of("name", "Enter your full name (at least 2 characters).")));
    }

    @ExceptionHandler(InvalidPreferredDateException.class)
    public ResponseEntity<ApiError> invalidPreferredDate() {
        return ResponseEntity.badRequest().body(new ApiError("VALIDATION_ERROR",
                "Check the highlighted fields.", Map.of("preferredDate", "Choose today or a future date.")));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> invalidRequest() {
        return ResponseEntity.badRequest()
                .body(new ApiError("INVALID_REQUEST", "The request body is invalid.", Map.of()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiError> persistenceUnavailable() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ApiError("SERVICE_UNAVAILABLE", "The enquiry could not be saved. Try again later.", Map.of()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> unexpectedFailure() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("INTERNAL_ERROR", "The enquiry could not be submitted.", Map.of()));
    }
}