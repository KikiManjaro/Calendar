package com.kikimanjaro.calendar.database.exception;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {
        super("Database not connected");
    }
}
