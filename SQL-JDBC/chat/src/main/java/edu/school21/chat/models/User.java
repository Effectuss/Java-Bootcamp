package edu.school21.chat.models;

import lombok.*;

import java.util.List;

@ToString(includeFieldNames=true)
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
public class User {
    private final Long id;
    @Setter
    private String login;
    @Setter
    private String password;
    private List<Chatroom> usedRooms;
    private List<Chatroom> createdRooms;
}