package com.futurodev.ativa365.exceptions;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<ProblemDetail> handleSignatureVerificationException(SignatureVerificationException exception) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException exception) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<ProblemDetail> handleJWTDecodeException(JWTDecodeException exception) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException exception) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }
}
