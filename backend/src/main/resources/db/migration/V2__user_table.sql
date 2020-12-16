CREATE TYPE gender_type AS ENUM ('MALE', 'FEMALE');

CREATE TABLE userregistry.user (
    id CHAR(36) NOT NULL,
    full_name VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    username VARCHAR(255) NULL,
    date_of_birth DATE NULL,
    gender gender_type NULL,
    photo TEXT NULL
);
