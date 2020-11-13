package com.example.practice.view;

import lombok.Data;

@Data
public class ResultView {
    private String result;

    public ResultView(String result) {
        this.result = result;
    }
}
