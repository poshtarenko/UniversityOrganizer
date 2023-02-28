--Users
ALTER TABLE Users
    ADD CONSTRAINT email_unique UNIQUE (email);
--Roles
ALTER TABLE Roles
    ADD CONSTRAINT kind_of_roles_name CHECK (name in ('USER', 'MODER', 'ADMIN'));
--Сourse
ALTER TABLE Course
    ADD CONSTRAINT course_number_check CHECK (num > 0 and num <= 6);

ALTER TABLE course
    ADD CONSTRAINT course_number_unique UNIQUE (num,user_id);
--Semester
ALTER TABLE Semester
    ADD CONSTRAINT semester_number_check CHECK (num > 0 and num <= 2);
-- UserSetting
ALTER TABLE UserSetting
    ADD CONSTRAINT user_setting_fk UNIQUE (user_id);
ALTER TABLE UserSetting
    ADD CONSTRAINT les_duration_check CHECK (lesson_duration > 0 and lesson_duration <= 180);
ALTER TABLE UserSetting
    ADD CONSTRAINT break_time_check CHECK (break_time > 0 and break_time <= 60);
-- Schedule
ALTER TABLE Schedule
    ADD CONSTRAINT unique_semester UNIQUE (semester_id);

-- ScheduleItem
ALTER TABLE ScheduleItem
    ADD CONSTRAINT lesson_num_check CHECK (lesson_num > 0 and lesson_num < 10);
ALTER TABLE ScheduleItem
    ADD CONSTRAINT weekday_enum CHECK (weekday in
                                       ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'));
ALTER TABLE ScheduleItem
    ADD CONSTRAINT lesson_type_enum CHECK (lesson_type in ('LECTURE', 'PRACTICE', 'LAB'));
ALTER TABLE ScheduleItem
    ADD CONSTRAINT week_type_enum CHECK (week_type in ('PAIR', 'NON_PAIR', 'ALL'));
DELETE FROM course where num = 1 and num  = 2;

