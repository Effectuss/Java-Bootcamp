package edu.school21.chat.models;

import lombok.*;

import java.util.List;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class Chatroom {
    private final int id;
    @Setter
    private String name;
    @Setter
    private User owner;
    private List<Message> messages;
}
