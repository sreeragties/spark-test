package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InputDataException.class})
    public ApiErrorResponse handle(InputDataException ex, WebRequest request) {
        return ApiErrorResponse.build(HttpStatus.BAD_GATEWAY, ex, request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiErrorResponse handle(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> messages = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        GenericException genericException = new GenericException(messages);
        return ApiErrorResponse.build(HttpStatus.BAD_GATEWAY, genericException, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {GenericException.class})
    public ApiErrorResponse handle(GenericException ex, WebRequest request) {
        return ApiErrorResponse.build(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ApiErrorResponse handle(Exception ex, WebRequest request) {
        String message = "Something went wrong. Please try again later.";
        GenericException genericException = new GenericException(message);
        return ApiErrorResponse.build(HttpStatus.INTERNAL_SERVER_ERROR, genericException, request);
    }
}
