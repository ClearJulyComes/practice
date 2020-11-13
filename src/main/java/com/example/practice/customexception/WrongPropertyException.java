package com.example.practice.customexception;

/**
 * Custom exception when expected parameter not found
 */
public class WrongPropertyException extends RuntimeException {
    public WrongPropertyException(String message) {
        super(message);
    }
}
