package com.alterrae.db.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity("users")
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String password;
    private String email;

    public User(String s, String username, String password, String email) {
        this(new ObjectId(s), username, password, email);
    }

    public String getStringId() {
        return this.getId().toString();
    }
}
