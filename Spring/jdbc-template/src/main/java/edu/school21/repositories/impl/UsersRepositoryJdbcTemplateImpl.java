package edu.school21.repositories.impl;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private static final String SAVE_QUERY = "INSERT INTO users (id, email) VALUES (:id, :email);";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", entity.getId())
                .addValue("email", entity.getEmail());
        jdbcTemplate.update(SAVE_QUERY, params);
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
