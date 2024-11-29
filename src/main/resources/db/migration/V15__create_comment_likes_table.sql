CREATE TABLE comment_likes (
user_id varchar(63),
post_id BIGINT REFERENCES comments(id) ON DELETE CASCADE
);