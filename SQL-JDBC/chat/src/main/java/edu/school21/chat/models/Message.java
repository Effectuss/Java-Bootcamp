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
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime date;

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
