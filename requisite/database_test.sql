CREATE DATABASE IF NOT EXISTS calendar_test;

CREATE TABLE IF NOT EXISTS calendar_test.activity
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    date LONG,
    annotation VARCHAR(255),
    status VARCHAR(13)
);