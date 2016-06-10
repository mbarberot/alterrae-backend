package com.sistearth.db.api.entity;

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

    @Property("created_at")
    private Date createdAt;

    @Reference
    private User author;

    public Post(String id, String title, String body, Date createdAt, User author) {
        this(new ObjectId(id), title, body, createdAt, author);
    }

    public String getStringId() {
        return this.getId().toString();
    }
}
