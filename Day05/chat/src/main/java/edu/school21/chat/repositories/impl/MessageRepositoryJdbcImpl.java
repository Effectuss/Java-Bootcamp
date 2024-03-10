package edu.school21.chat.repositories.impl;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepository;
import edu.school21.chat.repositories.exception.NotSavedSubEntityException;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@AllArgsConstructor
public class MessageRepositoryJdbcImpl implements MessageRepository {

    private final DataSource dataSource;
    private static final String FIND_BY_ID_QUERY = """
            SELECT
                messages.id AS message_id,
                messages.text,
                messages.message_date,
                chatrooms.id AS chatroom_id,
                chatrooms.name,
                users.id AS user_id,
                users.login,
                users.password
            FROM messages
            JOIN chatrooms ON messages.chatroom_id = chatrooms.id
            JOIN users ON messages.author_id = users.id
            WHERE messages.id = ?;
            """;

    private static final String SAVE_MESSAGE = """
            INSERT INTO messages (author_id, chatroom_id, text, message_date)
            VALUES (?, ?, ?, ?)
            RETURNING id;
            """;

    private static final String UPDATE_MESSAGE = """
            UPDATE messages SET author_id = ?,  chatroom_id = ?, text = ?, message_date = ?
            WHERE id = ?;
            """;


    @Override
    public Optional<Message> findById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
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
                            .date(resultSet.getTimestamp("message_date") != null ?
                                    resultSet.getTimestamp("message_date").toLocalDateTime() :
                                    null)
                            .build()
                    );
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_MESSAGE)) {

            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDate()));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    message.setId(id);
                }
            }

        } catch (NullPointerException | SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MESSAGE)) {

            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, message.getDate() != null ? Timestamp.valueOf(message.getDate()) : null);
            statement.setLong(5, message.getId());

            statement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            throw new NotSavedSubEntityException(e.getMessage(), e);
        }
    }
}
