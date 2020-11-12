package com.example.practice.controller;

import com.example.practice.view.DataError;
import com.example.practice.view.DataSuccess;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.hibernate.PropertyValueException;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object responseData, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (responseData.getClass()!=DataError.class) {
            DataSuccess data = new DataSuccess();
            data.setData(responseData);
            return data;
        }
        return responseData;
    }
    @ExceptionHandler(value = {EmptyResultDataAccessException.class, NoSuchElementException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Request value not found";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = {PropertyValueException.class })
    protected ResponseEntity<Object> handleValidationConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Non null property a null";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = { IllegalStateException.class })
    protected ResponseEntity<Object> handleSaveConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "No entity with this id";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
