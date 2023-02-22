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
CREATE TABLE Weekday
(
    id      BIGSERIAL PRIMARY KEY,
    weekday varchar(32) NOT NULL
);
CREATE TABLE Calendar
(
    lesson_num int PRIMARY KEY CHECK (lesson_num > 0 ),
    start_time time NOT NULL,
    end_time   time NOT NULL
);

CREATE TABLE DisciplineType
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    kind varchar(32)           NOT NULL
);
CREATE TABLE WeekType
(
    id   BIGSERIAL PRIMARY KEY,
    kind varchar(32) NOT NULL
);
CREATE TABLE Schedule
(
    user_id           BIGINT REFERENCES Users ON UPDATE CASCADE ON DELETE CASCADE,
    discipline_id     BIGINT REFERENCES Discipline ON UPDATE CASCADE,
    discipline_type_id BIGINT REFERENCES DisciplineType ON UPDATE CASCADE,
    weekday_id        BIGINT REFERENCES Weekday ON UPDATE CASCADE,
    calendar_id       BIGINT REFERENCES Calendar  ON UPDATE CASCADE,
    week_kind_id      BIGINT REFERENCES WeekType ON UPDATE CASCADE,
    PRIMARY KEY (user_id,discipline_id,discipline_type_id,weekday_id,calendar_id,week_kind_id)
);
DROP TABLE Schedule;
INSERT INTO Weekday (weekday)
VALUES ('Monday');
INSERT INTO Weekday (weekday)
VALUES ('Tuesday');
INSERT INTO Weekday (weekday)
VALUES ('Wednesday');
INSERT INTO Weekday (weekday)
VALUES ('Thursday');
INSERT INTO Weekday (weekday)
VALUES ('Friday');
INSERT INTO Weekday (weekday)
VALUES ('Saturday');
INSERT INTO Weekday (weekday)
VALUES ('Sunday');


INSERT INTO weektype (kind)
VALUES ('pair');
INSERT INTO weektype (kind)
VALUES ('not_pair');


INSERT INTO Calendar(lesson_num, start_time, end_time)
VALUES (1, '8:00', '9:35');
INSERT INTO Calendar(lesson_num, start_time, end_time)
VALUES (2, '9:50', '11:25');
INSERT INTO Calendar(lesson_num, start_time, end_time)
VALUES (3, '11:40', '13:15');
INSERT INTO Calendar(lesson_num, start_time, end_time)
VALUES (4, '13:30', '15:05');

INSERT INTO disciplinetype (kind)
VALUES ('Lecture');
INSERT INTO disciplinetype (kind)
VALUES ('Practice');
INSERT INTO disciplinetype (kind)
VALUES ('Lab');

SELECT * FROM Calendar;


INSERT INTO Semester(num, course_id)
VALUES (1, 1);

INSERT INTO Discipline (discipline_name, semester_id)
VALUES ('ОПИ', 1);
INSERT INTO Discipline (discipline_name, semester_id)
VALUES ('АВПЗ', 1);
INSERT INTO Discipline (discipline_name, semester_id)
VALUES ('КПЗ', 1);

INSERT INTO Schedule (user_id, discipline_id, discipline_type_id, weekday_id, calendar_id, week_kind_id)
                         VALUES (1, 1, 1, 1, 1,1);
INSERT INTO Schedule (user_id, discipline_id, discipline_type_id, weekday_id, calendar_id, week_kind_id)
VALUES (1, 1, 2, 1, 2,1);
INSERT INTO Schedule (user_id, discipline_id, discipline_type_id, weekday_id, calendar_id, week_kind_id)
VALUES (1, 1, 3, 2, 2,1);
INSERT INTO Schedule (user_id, discipline_id, discipline_type_id, weekday_id, calendar_id, week_kind_id)
VALUES (1, 2, 3, 2, 4,1);
INSERT INTO Schedule (user_id, discipline_id, discipline_type_id, weekday_id, calendar_id, week_kind_id)
VALUES (1, 3, 1, 3, 2,1);

SELECT * FROM Schedule;

SELECT email,dp.discipline_name,DisciplineType.kind,Weekday.weekday,lesson_num,WeekType.kind, start_time,end_time FROM Users as us  join Schedule as sch on us.id = sch.user_id
                            join  Discipline as dp on  sch.discipline_id = dp.id
                            join  DisciplineType on sch.discipline_type_id = DisciplineType.id
                            join weekday on sch.weekday_id = Weekday.id
                            join weektype on sch.week_kind_id = WeekType.id
                            join calendar on sch.calendar_id = Calendar.lesson_num
WHERE Weekday.weekday = 'Wednesday';


SELECT * FROM weekday;
DELETE FROM Schedule WHERE calendar_id = 3;
SELECT *
FROM Discipline;

SELECT *
FROM Course;
SELECT *
FROM Semester;
SELECT *
FROM Calendar;
INSERT
INTO Users (email, password)
values ('abc@gmail.com', 'abc');

INSERT INTO Course (num, user_id)
values (1, 1);

