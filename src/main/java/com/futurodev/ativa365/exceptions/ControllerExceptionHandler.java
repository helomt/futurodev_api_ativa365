package com.futurodev.ativa365.exceptions;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

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

    @ExceptionHandler(PersonCpfAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handlePersonCpfAlreadyExistsException(PersonCpfAlreadyExistsException exception){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(PersonEmailAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handlePersonEmailAlreadyExistsException(PersonEmailAlreadyExistsException exception){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ProblemDetail> handlePersonNotFoundException(PersonNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(UsernameNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCepNotFoundException(CepNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(LocalNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleLocalNotFoundException(LocalNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(PersonToBeDeletedIsNotTheCurrentUserException.class)
    public ResponseEntity<ProblemDetail> handlePersonToBeDeletedIsNotTheCurrentUserException(PersonToBeDeletedIsNotTheCurrentUserException exception){
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ProblemDetail> handleFeignException(FeignException exception){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ProblemDetail> handleSQLException(SQLException exception){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage() );
        problemDetail.setTitle(httpStatus.name());

        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

}
