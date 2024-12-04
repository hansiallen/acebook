ALTER TABLE direct_messages
ADD COLUMN reply_to_id bigint;

ALTER TABLE direct_messages
ADD CONSTRAINT fk_reply_to FOREIGN KEY (reply_to_id) REFERENCES direct_messages(id) ON DELETE CASCADE;