USE adlister_db;

# DROP TABLE IF EXISTS ads;
# DROP TABLE IF EXISTS users;

CREATE TABLE if not exists users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240) UNIQUE NOT NULL,
    email VARCHAR(240) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists ads(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(240) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE if not exists categories(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(240) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE if not exists category_and_ad (
    category_id INT UNSIGNED NOT NULL,
    ad_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (ad_id) REFERENCES ads(id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    UNIQUE (category_id, ad_id)
);

CREATE TABLE if not exists profiles (
    user_id INT UNSIGNED UNIQUE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    last_name VARCHAR(250),
    first_name VARCHAR(250),
    address VARCHAR(250),
    phone VARCHAR(250)
);
