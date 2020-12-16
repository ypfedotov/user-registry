CREATE TYPE gender_type AS ENUM ('MALE', 'FEMALE');

CREATE TABLE userregistry.user (
    id UUID PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    date_of_birth DATE NULL,
    gender gender_type NULL,
    photo TEXT NULL
);
