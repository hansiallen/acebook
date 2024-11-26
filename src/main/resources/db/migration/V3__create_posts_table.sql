DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
id bigserial PRIMARY KEY,
content varchar(255),
user_id varchar(63),
friends_only BOOLEAN,
date_time timestamp
);