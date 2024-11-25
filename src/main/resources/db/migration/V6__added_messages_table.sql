DROP TABLE IF EXISTS direct_messages;

CREATE TABLE direct_messages (
id bigserial PRIMARY KEY,
content varchar(255),
sender_id varchar(63),
receiver_id varchar(63),
date_time timestamp
);