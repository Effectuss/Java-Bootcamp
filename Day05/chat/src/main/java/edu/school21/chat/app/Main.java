package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.db.DbConfig;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.impl.MessageRepositoryJdbcImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

//            ex01(dataSource);
            ex02(dataSource);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private static void ex01(DataSource dataSource) {
        MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);

        System.out.println(messageRepositoryJdbc.findById(2L));
    }

    private static void ex02(DataSource dataSource) {
        User creator = new User(123L, "user", "user", new ArrayList<>(), new ArrayList<>());
        User author = creator;
        Chatroom chatroom = new Chatroom(5L, "room", creator, new ArrayList<>());
        Message message = new Message(null, author, chatroom, "Hello!!!", LocalDateTime.now());

        MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);
        messageRepositoryJdbc.save(message);
        System.out.println(messageRepositoryJdbc.findById(6));
    }
}
