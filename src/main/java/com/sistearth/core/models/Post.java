package com.sistearth.core.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Post implements Serializable {
    private int id;
    private String title;
    private String body;
}
