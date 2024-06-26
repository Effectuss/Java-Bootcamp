package edu.school21.service.services;

import edu.school21.service.config.TestApplicationConfig;
import edu.school21.service.services.exception.UsersServiceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceImplTest {
    private static UsersService usersService;

    @BeforeAll
    public static void setUp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestApplicationConfig.class);

        usersService = context.getBean("usersServiceJdbcTemplate", UsersService.class);
    }

    @Test
    void testSignUpWithValidEmail() {
        String email = "test@example.com";
        String password = usersService.signUp(email);

        assertNotNull(password);
        assertFalse(password.isEmpty());
    }

    @Test
    void testSignUpWithInvalidEmail() {
        String invalidEmail = "invalid-email";

        UsersServiceException exception = assertThrows(UsersServiceException.class, () -> {
            usersService.signUp(invalidEmail);
        });

        assertEquals(String.format("The format for email %s incorrect!", invalidEmail), exception.getMessage());
    }

    @Test
    void testSignUpWithNullEmail() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            usersService.signUp(null);
        });

        assertEquals("email is marked non-null but is null", exception.getMessage());
    }

    @Test
    void testSignUpWithEmptyEmail() {
        String emptyEmail = "";

        UsersServiceException exception = assertThrows(UsersServiceException.class, () -> {
            usersService.signUp(emptyEmail);
        });

        assertEquals(String.format("The format for email %s incorrect!", emptyEmail), exception.getMessage());
    }

    @Test
    void testSignUpWithWhitespaceEmail() {
        String whitespaceEmail = "   ";

        UsersServiceException exception = assertThrows(UsersServiceException.class, () -> {
            usersService.signUp(whitespaceEmail);
        });

        assertEquals(String.format("The format for email %s incorrect!", whitespaceEmail), exception.getMessage());
    }

}
