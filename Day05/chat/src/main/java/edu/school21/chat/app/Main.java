package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.db.DbConfig;
import edu.school21.chat.repositories.impl.MessageRepositoryJdbcImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            DbConfig dbConfig = new DbConfig();

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(dbConfig.getUrl());
            hikariConfig.setUsername(dbConfig.getUsername());
            hikariConfig.setPassword(dbConfig.getPassword());

            HikariDataSource dataSource = new HikariDataSource(hikariConfig);

            MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);

            System.out.println(messageRepositoryJdbc.findById(1L));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
