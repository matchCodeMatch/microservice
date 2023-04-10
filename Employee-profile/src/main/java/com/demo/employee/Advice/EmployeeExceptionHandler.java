package com.demo.employee.Advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import feign.FeignException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.UnexpectedTypeException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleConstraintViolationException(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            map.put(error.getField(),error.getDefaultMessage());
        });
        return map;
    }
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDateTimeParseException(DateTimeParseException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUnexpectedTypeException(UnexpectedTypeException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(InvalidFormatException.class)
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
//        return "Fields should not be null or incorrect field data.";
        return ex.getMessage();
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
        return "There was no profile for the given id."+ ex.getLocalizedMessage()+ex.getClass();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityMappedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEntityMappedException(EntityMappedException ex)
    {
        return "Entities are mapped and cannot be deleted.";
    }
    @ExceptionHandler(FeignException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFeignHandlerException(FeignException.BadRequest ex){
        return "Feign Client Bad Request Error."+ex.getLocalizedMessage();
//        Map map = new HashMap<>();
//        map.put("Message",ex.getMessage());
//        map.put("Request",ex.request());
//        map.put("Body",ex.responseBody());
//        map.put("Headers",ex.responseHeaders());
//        return map;
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

