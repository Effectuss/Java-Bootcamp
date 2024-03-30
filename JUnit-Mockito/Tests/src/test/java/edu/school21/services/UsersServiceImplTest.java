package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

class UsersServiceImplTest {

    private static final String CORRECT_LOGIN = "correct_login";
    private static final String CORRECT_PASSWORD = "correct_password";
    private static final String INCORRECT_LOGIN = "incorrect_login";
    private static final String INCORRECT_PASSWORD = "incorrect_password";

    @Mock
    private UserRepository userRepository;
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usersService = new UsersServiceImpl(userRepository);
    }

    @Test
    void testCorrectLoginAndPassword() throws SQLException, EntityNotFoundException, AlreadyAuthenticatedException {
        User user = new User(1L, CORRECT_LOGIN, CORRECT_PASSWORD, false);
        Mockito.when(userRepository.findByLogin(CORRECT_LOGIN)).thenReturn(user);

        Assertions.assertTrue(usersService.authenticate(CORRECT_LOGIN, CORRECT_PASSWORD));
        Assertions.assertTrue(user.isAuthenticated());

        Mockito.verify(userRepository).update(user);
    }

    @Test
    void testIncorrectLogin() throws SQLException, EntityNotFoundException {
        Mockito.when(userRepository.findByLogin(INCORRECT_LOGIN)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> usersService.authenticate(INCORRECT_LOGIN, CORRECT_PASSWORD));

        Mockito.verify(userRepository, Mockito.never()).update(Mockito.any());
    }

    @Test
    void testIncorrectPassword() throws AlreadyAuthenticatedException, SQLException, EntityNotFoundException {
        User user = new User(1L, CORRECT_LOGIN, CORRECT_PASSWORD, false);
        Mockito.when(userRepository.findByLogin(CORRECT_LOGIN)).thenReturn(user);

        Assertions.assertFalse(usersService.authenticate(CORRECT_LOGIN, INCORRECT_PASSWORD));

        Mockito.verify(userRepository, Mockito.never()).update(Mockito.any());
    }
}
