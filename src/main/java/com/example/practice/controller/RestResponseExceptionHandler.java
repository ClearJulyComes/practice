package com.example.practice.controller;

import com.example.practice.customexception.WrongPropertyException;
import com.example.practice.view.DataError;
import com.example.practice.view.DataSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Exception handler aspect
 */
@ControllerAdvice
public class RestResponseExceptionHandler implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

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
     * @return response body
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex) {
        String bodyOfResponse;
        DataError dataError;
        ResponseEntity.BodyBuilder result;
        UUID uuid = UUID.randomUUID();
        if (ex.getClass() == EmptyResultDataAccessException.class || ex.getClass()== NoSuchElementException.class) {
            bodyOfResponse = "Request value not found. Error UUID: " + uuid;
            logger.warn(bodyOfResponse, ex);
            result = ResponseEntity.status(HttpStatus.NOT_FOUND);
        }else if (ex.getClass() == WrongPropertyException.class){
            bodyOfResponse = "Wrong property selected. Error UUID: " + uuid;
            logger.warn(bodyOfResponse, ex);
            result = ResponseEntity.status(HttpStatus.NOT_FOUND);
        }else if (ex.getClass() == HttpMessageNotReadableException.class){
            bodyOfResponse = "Wrong property type. Error UUID: " + uuid;
            logger.warn(bodyOfResponse, ex);
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }else if (ex.getClass() == MethodArgumentNotValidException.class){
            bodyOfResponse = "Non null property a null. Error UUID: " + uuid;
            logger.warn(bodyOfResponse, ex);
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }else {
            bodyOfResponse = "Internal error. Error UUID: " + uuid;
            logger.error(bodyOfResponse, ex);
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        dataError = new DataError(bodyOfResponse);
        return result.body(dataError);
    }
}
