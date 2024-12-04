DROP TABLE IF EXISTS direct_messages;

CREATE TABLE direct_messages (
id bigserial PRIMARY KEY,
content varchar(255),
sender_id BIGINT,
receiver_id BIGINT,
date_time timestamp,
reply_to_id bigint,
CONSTRAINT fk_reply_to FOREIGN KEY (reply_to_id) REFERENCES direct_messages(id) ON DELETE CASCADE
);