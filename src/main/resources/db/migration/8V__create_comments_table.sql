DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
user_id varchar(63),
post_id INTEGER REFERENCES posts(id) ON DELETE CASCADE,
comments varchar(255),
date_time timestamp
);