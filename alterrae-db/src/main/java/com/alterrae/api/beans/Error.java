package com.alterrae.api.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {
    private String title;
    private String status;

    public Error(String status, String title) {
        this.status = status;
        this.title = title;
    }

    public Error(String status) {
        this.status = status;
    }
}
