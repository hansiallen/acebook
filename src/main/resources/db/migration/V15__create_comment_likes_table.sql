CREATE TABLE comment_likes (
user_id varchar(63),
comment_id BIGINT REFERENCES comments(id) ON DELETE CASCADE
);