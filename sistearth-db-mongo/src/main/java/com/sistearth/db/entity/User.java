package com.sistearth.db.entity;

import lombok.Data;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@Entity("users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
}
