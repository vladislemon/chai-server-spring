create table "user"
(
    id               uuid primary key,
    version          bigint    not null,
    name             text      not null unique,
    created_at       timestamp not null,
    last_modified_at timestamp not null
)