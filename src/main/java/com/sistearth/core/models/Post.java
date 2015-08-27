package com.sistearth.core.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Post implements Serializable {
    private int id;
    private String title;
    private String body;
    private Date created_at;
}
