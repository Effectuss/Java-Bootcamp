package edu.school21.service.application;

import edu.school21.service.config.ApplicationConfig;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        UsersService usersService = context.getBean("usersService", UsersService.class);

        System.out.println("OTP: " + usersService.signUp("aboba1@gmail.com"));
        System.out.println("OTP: " + usersService.signUp("Hehe@yandex.ru"));

        System.out.println("\nJdbcTemplate findAll call");
        var jdbcTemplateUsers = usersRepositoryJdbcTemplate.findAll();
        jdbcTemplateUsers.forEach(System.out::println);
    }
}
