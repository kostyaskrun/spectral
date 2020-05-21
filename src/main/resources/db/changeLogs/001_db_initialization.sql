CREATE TABLE IF NOT EXISTS countries
  (
      id          BIGSERIAL    NOT NULL,
      name        VARCHAR(255) NOT NULL,
      short_code  VARCHAR(2)   NOT NULL,
      created_at              TIMESTAMP    NOT NULL,
      updated_at              TIMESTAMP    NOT NULL,
      CONSTRAINT countries_pkey
          PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS roles
(
    id          BIGSERIAL    NOT NULL,
    name        VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP    NOT NULL,
    updated_at              TIMESTAMP    NOT NULL,
    CONSTRAINT role_pkey
        PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id          BIGSERIAL    NOT NULL,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    country_id  BIGSERIAL    NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    CONSTRAINT users_pkey
        PRIMARY KEY (id),
            CONSTRAINT uk_email
            UNIQUE (email),
            CONSTRAINT country_fkey
            FOREIGN KEY (country_id)
            REFERENCES countries (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id     BIGSERIAL REFERENCES users (id) ON UPDATE CASCADE,
    role_id     BIGSERIAL REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id)
);

