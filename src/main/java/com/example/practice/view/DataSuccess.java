package com.example.practice.view;

import lombok.Data;

@Data
public class DataSuccess {
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
