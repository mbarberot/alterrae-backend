package com.sistearth.db.beans;

import lombok.Data;

@Data
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

    public Error() {
    }
}
