package com.demo.projects.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import feign.FeignException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDateTimeParseException(DateTimeParseException ex){
        return ex.getMessage();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnexpectedTypeException(InvalidFormatException ex){
        return ex.getOriginalMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getLocalizedMessage();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNullPointerException(NullPointerException ex) {
        return "Fields should be more than 3 characters or not null.";
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        return "Avoid Duplicate Entry";
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({JsonMappingException.class,
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleJsonMappingException(Exception ex) {
        return "There was no profile for the given id.";
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityMappedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityMappedException(EntityMappedException ex)
    {
        return "Entities are mapped and cannot be deleted.";
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFeignHandlerException(FeignException.BadRequest ex){
        return "Feign Client Bad Request Error.";
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleFeignExceptionNotFound(FeignException.NotFound ex){
        return ex.getLocalizedMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(feign.RetryableException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public String handleFeignRetryableException(feign.RetryableException ex){
        return ex.getLocalizedMessage();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.ServiceUnavailable.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleServiceUnavailableException(FeignException.ServiceUnavailable ex) {
        return "Sorry, the Department Service is currently unavailable. Please try again later.";
//        return ex.getLocalizedMessage();
    }

}

