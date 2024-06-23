package edu.school21.models;

import lombok.Data;


@Data
public class User {

    private Long id;

    private String email;

    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
