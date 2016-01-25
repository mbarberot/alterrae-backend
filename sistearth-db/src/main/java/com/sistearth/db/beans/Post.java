package com.sistearth.db.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Post {
    private Integer id;
    private String title;
    private String body;
    private Date createdAt;
    private Integer author;
}
