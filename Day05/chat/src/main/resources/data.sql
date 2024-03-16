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
       (4, 2,'Bye!');

INSERT INTO user_chatroom (user_id, chatroom_id)
SELECT DISTINCT messages.author_id, messages.chatroom_id
FROM messages;


WITH need_users AS (
    SELECT id, login
    FROM users
    ORDER BY id
    LIMIT ? OFFSET ?
)
SELECT u.id AS user_id,
       u.login AS user_name,
       array_agg(c.id) AS created_chat_id,
       array_agg(c.name) AS created_chat_name,
       array_agg(uc.chatroom_id) AS used_chat_ids,
       array_agg(cc.name) AS used_chat_names
FROM need_users u
         LEFT JOIN chatrooms c ON u.id = c.owner_id
         LEFT JOIN user_chatroom uc ON uc.user_id = u.id
         LEFT JOIN chatrooms cc ON cc.id = uc.chatroom_id
GROUP BY u.id, u.login, c.id, c.name
ORDER BY u.id, c.id;



WITH need_users AS (
    SELECT id, login
    FROM users
    ORDER BY id
    LIMIT ? OFFSET ?
)
SELECT u.id AS user_id,
       u.login AS user_name,
       array_agg(DISTINCT c.id) AS created_chat_id,
       array_agg(DISTINCT c.name) AS created_chat_name,
       array_agg(DISTINCT uc.chatroom_id) AS used_chat_ids,
       array_agg(DISTINCT cc.name) AS used_chat_names
FROM need_users u
         LEFT JOIN (
    SELECT id, owner_id, name
    FROM chatrooms
    GROUP BY id, owner_id, name
) c ON u.id = c.owner_id
         LEFT JOIN (
    SELECT user_id, chatroom_id
    FROM user_chatroom
    GROUP BY user_id, chatroom_id
) uc ON uc.user_id = u.id
         LEFT JOIN (
    SELECT id, name
    FROM chatrooms
    GROUP BY id, name
) cc ON cc.id = uc.chatroom_id
GROUP BY u.id, u.login
ORDER BY u.id;