DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
user_id varchar(63),
post_id INTEGER REFERENCES posts(id) ON DELETE CASCADE
);