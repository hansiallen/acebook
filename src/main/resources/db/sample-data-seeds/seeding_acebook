DROP TABLE IF EXISTS friend_requests;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS direct_messages;
DROP TABLE IF EXISTS blocks;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS impression_counts;

CREATE TABLE friend_requests (
    requesting_user int,
    requested_user int
);

CREATE TABLE friends (
    user_a int,
    user_b int
);

CREATE SEQUENCE IF NOT EXISTS notifications_id;
CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    user_id int,
    message varchar(255),
    timestamp datetime
);

CREATE SEQUENCE IF NOT EXISTS users_id;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nickname varchar(255),
    email varchar(255),
    last_login datetime
);

CREATE SEQUENCE IF NOT EXISTS direct_messages_id;
CREATE TABLE direct_messages (
    id SERIAL PRIMARY KEY,
    sender_id int,
    reciever_id int,
    content varchar(255)
    datetime datetime
);

CREATE TABLE blocks (
    blocking_user int,
    blocked_user int
);

CREATE SEQUENCE IF NOT EXISTS comments_id;
CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    user_id int,
    post_id int,
    content varchar(255),
    datetime datetime
);


CREATE TABLE likes (
    user_id int,
    post_id int
);

CREATE SEQUENCE IF NOT EXISTS posts_id;
CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    content varchar(255),
    friends_only bool,
    datetime datetime,
    user_id int
);

CREATE TABLE impression_count (
    post_id int,
    count int
);



