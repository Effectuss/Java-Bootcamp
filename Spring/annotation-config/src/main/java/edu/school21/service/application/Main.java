package edu.school21.service.application;

import edu.school21.service.config.ApplicationConfig;
import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        UsersService usersService = context.getBean("usersService", UsersService.class);

        for (long i = 0; i < 15; ++i) {
            User userJdbc = new User(i++, "user" + i + "@JDBC.ru");
            User userJdbcTemplate = new User(i, "user" + i + "@JDBCTEMPLATE.ru");
            usersRepositoryJdbc.save(userJdbc);
            usersRepositoryJdbcTemplate.save(userJdbcTemplate);
        }

        System.out.println("Jdbc findAll call");
        var jdbcUsers = usersRepositoryJdbc.findAll();
        if (jdbcUsers != null) {
            jdbcUsers.forEach(System.out::println);
        }

        System.out.println("\n\n\nJdbcTemplate findAll call");
        var jdbcTemplateUsers = usersRepositoryJdbcTemplate.findAll();
        jdbcTemplateUsers.forEach(System.out::println);
    }
}
