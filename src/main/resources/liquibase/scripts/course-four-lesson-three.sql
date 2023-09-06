--liquibase formatted sql

--changeset olapteva:1
CREATE INDEX student_name_index ON student(name);
--changeset olapteva:2
CREATE INDEX faculty_name_color_index ON faculty(name);
