package edu.school21.repositories.impl;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import edu.school21.repositories.execption.UserRepositoryException;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private static final String SAVE_QUERY = "INSERT INTO users (id, email) VALUES (?, ?);";
    private static final String UPDATE_QUERY = "UPDATE users SET email = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?;";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users;";

    private static final String SAVE_USER_ERROR = "The user with id %d cant be save, because: %s";
    private static final String UPDATE_USER_ERROR = "The user with id %d cant be update, because: %s";
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)) {
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String err = String.format(SAVE_USER_ERROR, entity.getId(), e.getMessage());
            log.error(err);
            throw new UserRepositoryException(err, e);
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            String err = String.format(UPDATE_USER_ERROR, entity.getId(), e.getMessage());
            log.error(err);
            throw new UserRepositoryException(err, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {

        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
