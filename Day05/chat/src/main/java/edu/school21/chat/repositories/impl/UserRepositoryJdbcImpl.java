package edu.school21.chat.repositories.impl;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {
    private static final String FIND_ALL_QUERY = """
                        
            """;

    private final DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY)) {
            int offset = page * size;

        } catch (SQLException e) {
        }
        return null;
    }
}
