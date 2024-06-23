DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE
);