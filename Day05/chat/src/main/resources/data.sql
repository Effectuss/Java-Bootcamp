INSERT INTO users (login, password)
VALUES ('admin', 'admin'),
       ('Effectus', '123'),
       ('Englishk', 'hard_password'),
       ('Yulia', 'qwerty'),
       ('Sergey', '1q2w3e4r'),
       ('Vasya', '123qwe'),
       ('Tolya', '222');

INSERT INTO chatrooms (name, owner_id)
VALUES ('board_games', 5),
       ('Duna', 1),
       ('Java', 2),
       ('ADM', 1),
       ('music_club', 6),
       ('cinema', 3),
       ('super chat', 1);

INSERT INTO messages (author_id, chatroom_id, text)
VALUES (1, 1, 'Hello'),
       (2, 1, 'Hi'),
       (4, 2, 'Hi, guys'),
       (5, 2, 'I''m fine'),
       (1, 2, 'I''m ok'),
       (4, 2, 'Bye!'),
       (4, 2, 'Hahahah');

INSERT INTO user_chatroom (user_id, chatroom_id)
SELECT DISTINCT messages.author_id, messages.chatroom_id
FROM messages;

WITH need_users AS (SELECT id, login
                    FROM users
                    ORDER BY id
                    LIMIT ? OFFSET ?),
     created_chats AS (SELECT u.id              AS user_id,
                              u.login           AS user_name,
                              array_agg(c.id)   AS created_chat_id,
                              array_agg(c.name) AS created_chat_name
                       FROM need_users u
                                LEFT JOIN chatrooms c ON u.id = c.owner_id
                       GROUP BY u.id, u.login),
     used_chats AS (SELECT u.id               AS user_id,
                           u.login            AS user_name,
                           array_agg(cc.id)   AS used_chat_ids,
                           array_agg(cr.name) AS used_chat_names
                    FROM need_users u
                             LEFT JOIN user_chatroom uc ON u.id = uc.user_id
                             LEFT JOIN chatrooms cc ON uc.chatroom_id = cc.id
                             LEFT JOIN chatrooms cr ON uc.chatroom_id = cr.id
                    GROUP BY u.id, u.login)
SELECT c.user_id,
       c.user_name,
       c.created_chat_id,
       c.created_chat_name,
       u.used_chat_ids,
       u.used_chat_names
FROM created_chats c
         LEFT JOIN used_chats u ON c.user_id = u.user_id
ORDER BY c.user_id;