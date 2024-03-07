package edu.school21.chat.models;

import lombok.*;

import java.util.List;

@ToString(includeFieldNames=true)
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
public class User {
    private final long id;
    @Setter
    private String login;
    @Setter
    private String password;
    private List<Chatroom> rooms;
    private List<Chatroom> createdRooms;
}