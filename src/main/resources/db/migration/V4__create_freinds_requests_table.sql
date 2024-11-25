DROP TABLE IF EXISTS friend_requests;

CREATE TABLE friend_requests (
requesting_user varchar(63),
requested_user varchar(63)
);