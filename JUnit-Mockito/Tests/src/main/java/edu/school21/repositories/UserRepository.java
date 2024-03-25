package edu.school21.repositories;

import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;

import java.sql.SQLException;

public interface UserRepository {
    User findByLogin(String login) throws EntityNotFoundException, SQLException;

    void update(User user) throws EntityNotFoundException, SQLException;
}
