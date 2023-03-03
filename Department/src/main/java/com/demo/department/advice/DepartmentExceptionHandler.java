package com.demo.department.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import feign.FeignException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class DepartmentExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getLocalizedMessage();
    }
    @ExceptionHandler(EntityMappedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityMappedException(EntityMappedException ex) {
        return "Entities are mapped and cannot be deleted.";
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNullPointerException(NullPointerException ex) {
        return "Fields should not be null or Incorrect Field data.";
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return "Avoid Duplicate Entry";
    }
    @ExceptionHandler({JsonMappingException.class,
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJsonMappingException(Exception ex) {
        return "There was no profile for the given id.";
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFeignHandlerException(FeignException.BadRequest ex){
        return "Feign Client Bad Request Error.";
    }
    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFeignExceptionNotFound(FeignException.NotFound ex){
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler(feign.RetryableException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public String handleFeignRetryableException(feign.RetryableException ex){
        return ex.getLocalizedMessage();
    }
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleServiceUnavailableException(FeignException.ServiceUnavailable ex) {
        return "Sorry, the Department Service is currently unavailable. Please try again later.";
//        return ex.getLocalizedMessage();
    }

}

