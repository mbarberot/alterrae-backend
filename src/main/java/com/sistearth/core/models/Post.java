package com.sistearth.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private int id;

    private String title;

    private String body;

    @JsonProperty("created_at")
    private Date createdAt;

    private User author;
}
