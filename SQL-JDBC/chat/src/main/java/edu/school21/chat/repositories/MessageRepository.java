package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findById(long id);

    void save(Message message);

    void update(Message message);
}
