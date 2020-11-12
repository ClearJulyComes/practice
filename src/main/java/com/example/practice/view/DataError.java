package com.example.practice.view;

import lombok.Data;

@Data
public class DataError {
    private Object error;

    public DataError(Object error) {
        this.error = error;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
