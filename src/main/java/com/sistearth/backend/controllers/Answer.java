package com.sistearth.backend.controllers;

import lombok.Data;

@Data
public class Answer {
    private int status;
    private String body;

    public Answer (int status, String body){
        this.status = status;
        this.body = body;
    }

    public Answer(int status) {
        this(status, "");
    }
}
