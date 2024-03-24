ALTER TABLE products
    ALTER COLUMN id RESTART WITH 1;

INSERT INTO products (name, price)
VALUES ('Tomato', 23.30),
       ('Orange', 30.33),
       ('Rice', 33.20),
       ('Computer', 993999.22),
       ('Laptop', 33333.99);