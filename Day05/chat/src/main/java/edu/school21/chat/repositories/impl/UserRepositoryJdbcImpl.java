package edu.school21.chat.repositories.impl;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String FIND_ALL_QUERY = """
            WITH need_users AS (SELECT id, login
                                FROM users
                                ORDER BY id
                                LIMIT ? OFFSET ?),
                 created_chats AS (SELECT u.id              AS user_id,
                                          u.login           AS user_name,
                                          array_agg(c.id)   AS created_chat_id,
                                          array_agg(c.name) AS created_chat_name
                                   FROM need_users u
                                            LEFT JOIN chatrooms c ON u.id = c.owner_id
                                   GROUP BY u.id, u.login),
                 used_chats AS (SELECT u.id               AS user_id,
                                       u.login            AS user_name,
                                       array_agg(cc.id)   AS used_chat_ids,
                                       array_agg(cr.name) AS used_chat_names
                                FROM need_users u
                                         LEFT JOIN user_chatroom uc ON u.id = uc.user_id
                                         LEFT JOIN chatrooms cc ON uc.chatroom_id = cc.id
                                         LEFT JOIN chatrooms cr ON uc.chatroom_id = cr.id
                                GROUP BY u.id, u.login)
            SELECT c.user_id,
                   c.user_name,
                   c.created_chat_id,
                   c.created_chat_name,
                   u.used_chat_ids,
                   u.used_chat_names
            FROM created_chats c
                     LEFT JOIN used_chats u ON c.user_id = u.user_id
            ORDER BY c.user_id;           
            """;

    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            int offset = page * size;
            statement.setInt(1, size);
            statement.setInt(2, offset);

            try (ResultSet resultSet = statement.getResultSet()) {
                List<User> users = new ArrayList<>();

                while (resultSet.next()) {
                }
            }
        }
        return null;
    }
}
