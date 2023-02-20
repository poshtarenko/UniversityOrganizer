CREATE TABLE Users
(
    id       BIGSERIAL PRIMARY KEY,
    email    varchar(128),
    password varchar(128)
);

CREATE table Roles
(
    id   BIGSERIAL PRIMARY KEY,
    name varchar(32)
);

create table user_role
(
    user_id bigserial references Users (id),
    role_id bigserial references Roles (id)
);

CREATE TABLE Course
(
    id      BIGSERIAL PRIMARY KEY,
    num     int,
    user_id BIGINT REFERENCES Users ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Semester
(
    id        BIGSERIAL PRIMARY KEY,
    num       int,
    course_id BIGINT REFERENCES Course ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Discipline
(
    id          BIGSERIAL PRIMARY KEY,
    name        varchar(128),
    semester_id BIGINT REFERENCES Semester ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Folder
(
    id            BIGSERIAL PRIMARY KEY,
    name          varchar(128),
    discipline_id BIGINT REFERENCES Discipline ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE File
(
    id          BIGSERIAL PRIMARY KEY,
    name        varchar(128),
    path        varchar(128),
    upload_time timestamp,
    folder_id   BIGINT REFERENCES Folder ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT
INTO Users (email, password)
values ('abc@gmail.com', 'abc');

INSERT INTO Course (num, user_id)
values (1, 1);

