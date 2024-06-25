package edu.school21.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    public User(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}