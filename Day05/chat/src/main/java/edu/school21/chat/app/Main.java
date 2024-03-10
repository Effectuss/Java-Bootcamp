package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.db.DbConfig;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessageRepository;
import edu.school21.chat.repositories.exception.NotSavedSubEntityException;
import edu.school21.chat.repositories.impl.MessageRepositoryJdbcImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            DbConfig dbConfig = new DbConfig();

            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(dbConfig.getUrl());
            hikariConfig.setUsername(dbConfig.getUsername());
            hikariConfig.setPassword(dbConfig.getPassword());

            try (HikariDataSource dataSource = new HikariDataSource(hikariConfig)) {

                ex01(dataSource);
                ex02(dataSource);
                ex03(dataSource);
            }

        } catch (NotSavedSubEntityException | IOException e) {
            log.error(e.getMessage());
        }
    }

    private static void ex01(DataSource dataSource) {
        MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);

        System.out.println("################### EX01 ###################");
        System.out.println(messageRepositoryJdbc.findById(2L) + "\n");
    }

    private static void ex02(DataSource dataSource) {
        User creator = new User(1L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom chatroom = new Chatroom(5L, "room", creator, new ArrayList<>());
        Message message = new Message(null, creator, chatroom, "Hello!!!", LocalDateTime.now());

        MessageRepositoryJdbcImpl messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);
        messageRepositoryJdbc.save(message);

        System.out.println("################### EX02 ###################");
        System.out.println("Message id is " + message.getId() + "\n");
    }

    private static void ex03(DataSource dataSource) {
        MessageRepository messageRepositoryJdbc = new MessageRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messageRepositoryJdbc.findById(6);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAuthor(new User(5L, "user", "user", new ArrayList<>(), new ArrayList<>()));
            message.setText(null);
            message.setDate(null);
            messageRepositoryJdbc.update(message);
            System.out.println("################### EX03 ###################");
            System.out.println(messageRepositoryJdbc.findById(6));
        }
    }
}
