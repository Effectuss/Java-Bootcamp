CREATE TABLE IF NOT EXISTS user (
	id SERIAL PRIMARY KEY,
	login VARCHAR(50),
	password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS chatroom (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE,
	owner_id INTEGER NOT NULL,
	FOREIGN KEY (owner_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS message (
	id SERIAL PRIMARY KEY,
	author_id INTEGER NOT NULL,
	chatroom_id INTEGER NOT NULL,
	text text NOT NULL,
	message_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (author_id) REFERENCES user(id),
	FOREIGN KEY (chatroom_id) REFERENCES chatroom(id)
);

CREATE TABLE IF NOT EXISTS user_chatroom (
    user_id INTEGER,
    chatroom_id INTEGER,
    PRIMARY KEY (user_id, chatroom_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (chatroom_id) REFERENCES chatroom(id)
);