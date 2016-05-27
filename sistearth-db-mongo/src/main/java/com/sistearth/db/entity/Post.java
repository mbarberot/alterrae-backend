package com.sistearth.db.entity;

import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;

@Data
@Entity("posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String body;
    private Date createdAt;

    @Reference
    private User author;
}