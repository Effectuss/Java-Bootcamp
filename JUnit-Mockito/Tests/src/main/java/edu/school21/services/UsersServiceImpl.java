package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import lombok.NonNull;

import java.sql.SQLException;

public class UsersServiceImpl {
    private static final String ALREADY_AUTHENTICATED_ERROR =
            "This user {login = %s}, already authenticated!";
    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean authenticate(@NonNull String login, @NonNull String password)
            throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        User currentUser = userRepository.findByLogin(login);
        if (currentUser.isAuthenticated()) {
            throw new AlreadyAuthenticatedException(String.format(ALREADY_AUTHENTICATED_ERROR, login));
        }

        if (password.equals(currentUser.getPassword())) {
            currentUser.setAuthenticated(true);
            userRepository.update(currentUser);
            return true;
        }

        return false;
    }
}
