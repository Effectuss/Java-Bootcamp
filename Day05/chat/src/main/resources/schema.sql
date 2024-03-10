DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS chatrooms CASCADE;
DROP TABLE IF EXISTS messages CASCADE;
DROP TABLE IF EXISTS user_chatroom CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS chatrooms
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50) NOT NULL UNIQUE,
    owner_id INTEGER     NOT NULL,
    CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id           SERIAL PRIMARY KEY,
    author_id    INTEGER NOT NULL,
    chatroom_id  INTEGER NOT NULL,
    text         text    NOT NULL,
    message_date TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    CONSTRAINT author_id_fk FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT chatroom_id_fk FOREIGN KEY (chatroom_id) REFERENCES chatrooms (id)
);

CREATE TABLE IF NOT EXISTS user_chatroom
(
    user_id     INTEGER,
    chatroom_id INTEGER,
    PRIMARY KEY (user_id, chatroom_id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT chatroom_id_fk FOREIGN KEY (chatroom_id) REFERENCES chatrooms (id)
);