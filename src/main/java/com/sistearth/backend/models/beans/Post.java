package com.sistearth.backend.models.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer id;

    private String title;

    private String body;

    @JsonProperty("created_at")
    private Date createdAt;

    private Integer author;
}
