package edu.school21.service.config;

import edu.school21.service.repositories.UsersRepositoryJdbcTemplateImpl;
import edu.school21.service.services.UsersService;
import edu.school21.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class TestApplicationConfig {

    @Bean("dataSource")
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .build();
    }

    @Bean("usersServiceJdbcTemplate")
    UsersService usersServiceJdbcTemplate(EmbeddedDatabase dataSource) {
        return new UsersServiceImpl(new UsersRepositoryJdbcTemplateImpl(dataSource));
    }

}
