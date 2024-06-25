package edu.school21.repositories.impl;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private static final String SAVE_USER_QUERY = "INSERT INTO users (id, email) VALUES (:id, :email);";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = :email WHERE id = :id;";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = :id;";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = :email;";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = :id;";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users;";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<User> rowMapper = (resultSet, rowNum) -> {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        return user;
    };

    @Override
    public User findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(FIND_BY_ID_QUERY, params, rowMapper).stream().findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL_USERS_QUERY, rowMapper);
    }

    @Override
    public void save(User entity) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", entity.getId())
                .addValue("email", entity.getEmail());
        jdbcTemplate.update(SAVE_USER_QUERY, params);
    }

    @Override
    public void update(User entity) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("email", entity.getEmail())
                .addValue("id", entity.getId());
        jdbcTemplate.update(UPDATE_USER_QUERY, params);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(DELETE_USER_QUERY, params);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
        List<User> users = jdbcTemplate.query(FIND_BY_EMAIL_QUERY, params, rowMapper);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }
}
