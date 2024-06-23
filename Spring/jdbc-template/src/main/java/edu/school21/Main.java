package edu.school21;


import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);

        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        for (long i = 0; i < 15; ++i) {
            User userJdbc = new User(i++, "user" + i + "@JDBC.ru");
            User userJdbcTemplate = new User(i, "user" + i + "@JDBCTEMPLATE.ru");
            usersRepositoryJdbc.save(userJdbc);
            usersRepositoryJdbcTemplate.save(userJdbcTemplate);
        }

        System.out.println(usersRepositoryJdbc.findAll());
        System.out.println(usersRepositoryJdbcTemplate.findAll());
    }
}
