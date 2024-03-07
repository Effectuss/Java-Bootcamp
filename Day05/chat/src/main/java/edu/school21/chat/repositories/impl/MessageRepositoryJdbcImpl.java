package edu.school21.chat.repositories.impl;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepository;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class MessageRepositoryJdbcImpl implements MessageRepository {

    private final DataSource dataSource;

    @Override
    public Optional<Message> findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     """
                                   SELECT\s
                                     messages.id as message_id,\s
                                     messages.text,
                                     messages.message_date,
                                     chatrooms.id as chatroom_id,
                                     chatrooms.name,
                                     users.id as user_id,
                                     users.login,
                                     users.password
                                 FROM messages
                                 JOIN chatrooms ON messages.chatroom_id = chatrooms.id
                                 JOIN users ON messages.author_id = users.id
                                 WHERE messages.id = ?;
                             """)) {
            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User sender = User.builder()
                            .id(resultSet.getLong("user_id"))
                            .login(resultSet.getString("login"))
                            .password(resultSet.getString("password"))
                            .build();

                    return Optional.of(Message.builder()
                            .id(resultSet.getLong("message_id"))
                            .author(sender)
                            .chatroom(Chatroom.builder()
                                    .id(resultSet.getLong("chatroom_id"))
                                    .name(resultSet.getString("name"))
                                    .owner(null)
                                    .build())
                            .text(resultSet.getString("text"))
                            .date(resultSet.getTimestamp("message_date").toLocalDateTime())
                            .build()
                    );
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
