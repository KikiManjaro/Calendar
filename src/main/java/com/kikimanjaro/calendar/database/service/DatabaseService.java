package com.kikimanjaro.calendar.database.service;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseService implements IDatabaseService {

    protected static Logger log = LoggerFactory.getLogger(DatabaseService.class);

    private static DatabaseService instance;

    protected boolean connected;
    protected Connection con;
    protected Statement stmt;
    protected String propertiesFileName = "jdbc.properties";

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    private DatabaseService() {
        connect();
    }

    public Properties loadPropertiesFile() {
        Properties prop = new Properties();
        try (InputStream in = new FileInputStream(propertiesFileName);) {
            prop.load(in);
        } catch (IOException e) {
            log.error("Cant load properties file", e);
        }
        return prop;
    }

    protected void connect() {
        try {
            Properties prop = loadPropertiesFile();
            if (!prop.isEmpty()) {
                String driverClass = prop.getProperty("MYSQLJDBC.driver");
                String url = prop.getProperty("MYSQLJDBC.url");
                String username = prop.getProperty("MYSQLJDBC.username");
                String password = prop.getProperty("MYSQLJDBC.password");

                Class.forName(driverClass);
                con = DriverManager.getConnection(url, username, password);
                stmt = con.createStatement();
                connected = true;
            } else {
                connected = false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Can't connect to database", e);
            connected = false;
        }
    }

    @Override
    public List<IActivity> getActivitiesFromDate(long timestamp) throws DatabaseConnectionException {
        if (connected) {
            List<IActivity> activityList = new ArrayList<>();
            ResultSet rs;
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement("SELECT id, date, annotation, status FROM activity WHERE date = ?;");
                preparedStatement.setLong(1, timestamp);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    IActivity activity = new Activity(rs.getInt("id"), rs.getLong("date"), rs.getString("annotation"), ActivityStatus.fromString(rs.getString("status")));
                    activityList.add(activity);
                }
                rs.close();
            } catch (SQLException e) {
                log.error("Can't find activities", e);
            } finally {
                closeStatement(preparedStatement);
            }
            return activityList;
        } else {
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void registerActivity(long date, String annotation, String status) throws DatabaseConnectionException {
        if (connected) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement("INSERT INTO activity (date, annotation, status) VALUES (?,?,?)");
                preparedStatement.setLong(1, date);
                preparedStatement.setString(2, annotation);
                preparedStatement.setString(3, status);
                preparedStatement.execute();
            } catch (SQLException e) {
                log.error("Can't register activity", e);
            } finally {
                closeStatement(preparedStatement);
            }
        } else {
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void updateActivity(int id, long date, String annotation, String status) throws DatabaseConnectionException {
        if (connected) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement("UPDATE activity SET date = ?, annotation = ?, status = ? WHERE id = ?");
                preparedStatement.setLong(1, date);
                preparedStatement.setString(2, annotation);
                preparedStatement.setString(3, status);
                preparedStatement.setInt(4, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                log.error("Can't update activity", e);
            } finally {
                closeStatement(preparedStatement);
            }
        } else {
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void deleteActivity(int id) throws DatabaseConnectionException {
        if (connected) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = con.prepareStatement("DELETE FROM activity WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                log.error("Can't delete activity", e);
            } finally {
                closeStatement(preparedStatement);
            }
        } else {
            throw new DatabaseConnectionException();
        }
    }

    private void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                log.error("Can't close statement", throwables);
            }
        }
    }
}
