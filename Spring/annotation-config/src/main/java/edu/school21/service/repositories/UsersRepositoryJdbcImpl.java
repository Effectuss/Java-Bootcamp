package edu.school21.service.repositories;

import edu.school21.service.models.User;
import edu.school21.service.repositories.execption.UserRepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component("usersRepositoryJdbc")
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private static final String SAVE_QUERY = "INSERT INTO users (id, email) VALUES (?, ?);";
    private static final String UPDATE_QUERY = "UPDATE users SET email = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?;";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT users.id, users.email FROM users WHERE email = ?;";
    private static final String FIND_BY_ID_QUERY = "SELECT users.id, users.email FROM users WHERE id = ?;";
    private static final String FIND_ALL_USERS_QUERY = "SELECT users.id, users.email FROM users;";

    private static final String SAVE_USER_ERROR = "The user with id %d cant be save, because: %s";
    private static final String UPDATE_USER_ERROR = "The user with id %d cant be update, because: %s";
    private static final String DELETE_USER_ERROR = "The user with id %d cant be update, because: %s";
    private final DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("driverManagerDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new UserRepositoryException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_QUERY)) {
            while (resultSet.next()) {
                users.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new UserRepositoryException(e.getMessage(), e);
        }
        return users;
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            String err = String.format(DELETE_USER_ERROR, id, e.getMessage());
            log.error(err);
            throw new UserRepositoryException(err, e);
        }
    }


    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}