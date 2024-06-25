package edu.school21.service.services;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.exception.UsersServiceException;
import edu.school21.service.utils.EmailValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplate") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(@NonNull String email) {
        if (!EmailValidator.isValidEmail(email)) {
            throw new UsersServiceException(String.format("The format for email %s incorrect!", email));
        }

        try {
            User user = new User();
            user.setEmail(email);
            usersRepository.save(user);
            return UUID.randomUUID().toString();
        } catch (Exception e) {
            throw new UsersServiceException(String.format("The users with email %s cant be registered!", email), e);
        }
    }

}
