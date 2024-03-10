package edu.school21.chat.models;

import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Message {
    private Long id;
    private final User author;
    private final Chatroom chatroom;
    private String text;
    private final LocalDateTime date;

    @Override
    public String toString() {
        return "Message{\n" +
                "id=" + id + ",\n" +
                "author=" + author + ",\n" +
                "chatroom=" + chatroom + ",\n" +
                "text='" + text + '\'' + ",\n" +
                "date=" + date + "\n" +
                '}';
    }
}
