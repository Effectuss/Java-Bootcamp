package edu.school21.chat.models;

import lombok.*;

import java.util.List;

@ToString(includeFieldNames = true)
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
public class Chatroom {
    private final long id;
    @Setter
    private String name;
    @Setter
    private User owner;
    private List<Message> messages;
}
