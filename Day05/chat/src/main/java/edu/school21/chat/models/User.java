package edu.school21.chat.models;

import lombok.*;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class User {
    private final int id;
    @Setter
    private String login;
    @Setter
    private String password;
}