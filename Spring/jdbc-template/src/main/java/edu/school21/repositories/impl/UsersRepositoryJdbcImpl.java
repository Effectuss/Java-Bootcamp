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

    private static final String SAVE_USER_ERROR = "The user cant be save, because: %s";
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
            log.error(String.format(SAVE_USER_ERROR, e.getMessage()));
            throw new UserRepositoryException(SAVE_USER_ERROR, e);
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findByEmail() {
        return Optional.empty();
    }
}
