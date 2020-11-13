package com.example.practice.controller;

import com.example.practice.customexception.WrongPropertyException;
import com.example.practice.view.DataError;
import com.example.practice.view.DataSuccess;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

/**
 * Exception handler aspect
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * Method for wrapping and handling response body
     * @param responseData response data
     * @param methodParameter method parameter
     * @param mediaType media type
     * @param aClass a class
     * @param serverHttpRequest server http request
     * @param serverHttpResponse server http response
     * @return response object
     */
    @Override
    public Object beforeBodyWrite(Object responseData, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (responseData.getClass()!=DataError.class) {
            DataSuccess data = new DataSuccess();
            data.setData(responseData);
            return data;
        }
        return responseData;
    }

    /**
     * Handling exception when finding value not found
     * @param ex exception
     * @param request request
     * @return response body
     */
    @ExceptionHandler(value = {EmptyResultDataAccessException.class, NoSuchElementException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Request value not found";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handling exception when selected wrong value
     * @param ex exception
     * @param request request
     * @return response body
     */
    @ExceptionHandler(value = { WrongPropertyException.class })
    protected ResponseEntity<Object> handleSaveConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Wrong property selected";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handling exception when request param have wrong type
     * @param ex exception
     * @param headers headers
     * @param status status
     * @param request request
     * @return response body
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String bodyOfResponse = "Wrong property type";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    /**
     * Handling situation when non null value a null
     * @param ex exception
     * @param headers headers
     * @param status status
     * @param request request
     * @return response body
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String bodyOfResponse = "Non null property a null";
        DataError dataError = new DataError(bodyOfResponse);
        return handleExceptionInternal(ex, dataError,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
