package edu.school21.chat.repositories.impl;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            int offset = page * size;
            statement.setInt(1, size);
            statement.setInt(2, offset);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getLong("user_id"))
                            .login(resultSet.getString("user_name"))
                            .createdRooms(getCreatedRooms(resultSet))
                            .usedRooms(getUsedRooms(resultSet))
                            .build();

                    users.add(user);
                }
            }
        }

        return users;
    }

    private List<Chatroom> getCreatedRooms(ResultSet resultSet) throws SQLException {
        return getRoomsFromResultSet(resultSet, "created_chat_id", "created_chat_name");
    }

    private List<Chatroom> getUsedRooms(ResultSet resultSet) throws SQLException {
        return getRoomsFromResultSet(resultSet, "used_chat_ids", "used_chat_names");
    }

    private List<Chatroom> getRoomsFromResultSet(ResultSet resultSet, String idColumn, String nameColumn)
            throws SQLException {
        Array idsArray = resultSet.getArray(idColumn);
        Array namesArray = resultSet.getArray(nameColumn);

        if (idsArray == null || namesArray == null) {
            return Collections.emptyList();
        }

        Integer[] ids = (Integer[]) idsArray.getArray();
        String[] names = (String[]) namesArray.getArray();

        List<Chatroom> rooms = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            rooms.add(Chatroom.builder()
                    .id(ids[i] == null ? null : Long.valueOf(ids[i]))
                    .owner(null) // в задание сказано что каждая подсущность не должна включать свои зависимости
                    .name(names[i])
                    .build());
        }

        return rooms;
    }
}
