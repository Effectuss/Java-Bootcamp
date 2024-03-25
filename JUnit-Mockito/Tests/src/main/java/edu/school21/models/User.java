package edu.school21.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private Long id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private boolean isAuthenticated;
}
