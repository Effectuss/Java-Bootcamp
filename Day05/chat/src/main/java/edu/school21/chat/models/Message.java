package edu.school21.chat.models;

import lombok.*;

import java.time.LocalDateTime;

@ToString(includeFieldNames=true)
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
public class Message {
    private final long id;
    private final User author;
    private final Chatroom chatroom;
    @Setter
    private String text;
    private final LocalDateTime date;
}
