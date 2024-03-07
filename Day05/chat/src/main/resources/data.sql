INSERT INTO users (login, password)
VALUES ('admin', 'admin'),
       ('Effectus', '123'),
       ('Englishk', 'hard_password'),
       ('Yulia', 'qwerty'),
       ('Sergey', '1q2w3e4r'),
       ('Vasya', '123qwe');

INSERT INTO chatrooms (name, owner_id)
VALUES ('board_games', 5),
       ('Java', 2),
       ('ADM', 1),
       ('music_club', 6),
       ('cinema', 3);

INSERT INTO messages (author_id, chatroom_id, text)
VALUES (1, 1, 'Hello'),
       (2, 1, 'Hi'),
       (4, 2, 'Hi, guys'),
       (5, 2, 'I''m fine'),
       (1, 2, 'I''m ok');

INSERT INTO user_chatroom (user_id, chatroom_id)
SELECT messages.author_id, messages.chatroom_id
FROM messages;

