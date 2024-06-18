package com.projucti.dspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidExceptions(MethodArgumentNotValidException exceptions){
        Map<String, String> exceptionMap= new HashMap<>();
        exceptions.getBindingResult().getAllErrors().forEach((error)->{
            String errorName= ((FieldError)error).getField();
            String message= error.getDefaultMessage();
            exceptionMap.put(errorName,message);
        });

        return new ResponseEntity<>(exceptionMap, HttpStatus.BAD_REQUEST);
    }
}
