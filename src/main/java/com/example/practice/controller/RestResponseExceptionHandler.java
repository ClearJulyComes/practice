package com.example.practice.controller;

import com.example.practice.customexception.WrongPropertyException;
import com.example.practice.view.DataError;
import com.example.practice.view.DataSuccess;
import org.springframework.core.MethodParameter;
import org.springframework.dao.EmptyResultDataAccessException;
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

import java.util.NoSuchElementException;

/**
 * Exception handler aspect
 */
@ControllerAdvice
public class RestResponseExceptionHandler implements ResponseBodyAdvice<Object> {
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
     * Handling exception
     * @param ex exception
     * @param request request
     * @return response body
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {
        String bodyOfResponse;
        if (ex.getClass() == EmptyResultDataAccessException.class || ex.getClass()== NoSuchElementException.class) {
            bodyOfResponse = "Request value not found";
        }else if (ex.getClass() == WrongPropertyException.class){
            bodyOfResponse = "Wrong property selected";
        }else if (ex.getClass() == HttpMessageNotReadableException.class){
            bodyOfResponse = "Wrong property type";
        }else if (ex.getClass() == MethodArgumentNotValidException.class){
            bodyOfResponse = "Non null property a null";
        }else {
            bodyOfResponse = "Internal error";
        }
        DataError dataError = new DataError(bodyOfResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataError);
    }
}
