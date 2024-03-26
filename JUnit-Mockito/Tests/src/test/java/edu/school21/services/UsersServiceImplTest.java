package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;


public class UsersServiceImplTest {
    private static final String CORRECT_LOGIN = "CORRECT_LOGIN";
    private static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    private static final String CORRECT_PASSWORD = "CORRECT_PASSWORD";
    private static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";

    private UserRepository userRepository;
    private UsersServiceImpl usersService;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, CORRECT_LOGIN, CORRECT_PASSWORD, false);
        userRepository = Mockito.mock(UserRepository.class);
        usersService = new UsersServiceImpl(userRepository);
    }

    @Test
    void testCorrectLoginAndPassword() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        Mockito.when(userRepository.findByLogin(CORRECT_LOGIN)).thenReturn(user);

        Assertions.assertTrue(usersService.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD));
        Assertions.assertTrue(user.isAuthenticated());
        Mockito.verify(userRepository).update(user);
    }

    @Test
    void testIncorrectLogin() throws SQLException, EntityNotFoundException {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userRepository.findByLogin(INCORRECT_LOGIN));

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate(INCORRECT_LOGIN, CORRECT_PASSWORD));

        Mockito.verify(userRepository, Mockito.never()).update(user);
    }

    @Test
    void testIncorrectPassword() throws AlreadyAuthenticatedException {

    }
}
