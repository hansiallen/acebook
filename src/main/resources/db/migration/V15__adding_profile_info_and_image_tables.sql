CREATE TABLE profile_pages (
    user_id TEXT,
    first_name VARCHAR(50) DEFAULT NULL,
    last_name VARCHAR(50) DEFAULT NULL,
    profile_picture_url TEXT DEFAULT '/images/profile-picture-default.png',
    bio TEXT,
    location VARCHAR(100),
    website_url TEXT,
    is_public BOOLEAN DEFAULT TRUE
);