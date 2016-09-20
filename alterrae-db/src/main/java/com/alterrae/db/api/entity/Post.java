package com.alterrae.db.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity("posts")
public class Post {
    @Id
    private ObjectId id;
    private String title;
    private String body;
    private Date creationDate;

    @Reference
    private User author;

    public Post(String id, String title, String body, Date creationDate, User author) {
        this(new ObjectId(id), title, body, creationDate, author);
    }

    public String getStringId() {
        return this.getId().toString();
    }
}
