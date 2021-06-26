package com.kikimanjaro.calendar.database.service;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements IDatabaseService {
    protected boolean connected;
    protected Connection con;
    protected Statement stmt;

    private static DatabaseService instance;

    protected String ipAddress = "127.0.0.1";
    protected String port = "3306";
    protected String dbName = "calendar";
    protected String user = "root";
    protected String password = "root";

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    private DatabaseService() {
        connect();
    }

    protected void connect() {
        String url = "jdbc:mysql://" + ipAddress + ":" + port + "/" + dbName + "?useTimezone=true&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            connected = true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
                preparedStatement = con.prepareStatement("SELECT id, date, annotation, status FROM " + dbName + ".activity WHERE date = ?;");
                preparedStatement.setLong(1, timestamp);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    IActivity activity = new Activity(rs.getInt("id"), rs.getLong("date"), rs.getString("annotation"), ActivityStatus.fromString(rs.getString("status")));
                    activityList.add(activity);
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
                preparedStatement = con.prepareStatement("INSERT INTO " + dbName + ".activity (date, annotation, status) VALUES (?,?,?)");
                preparedStatement.setLong(1, date);
                preparedStatement.setString(2, annotation);
                preparedStatement.setString(3, status);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
                preparedStatement = con.prepareStatement("UPDATE " + dbName + ".activity SET date = ?, annotation = ?, status = ? WHERE id = ?");
                preparedStatement.setLong(1, date);
                preparedStatement.setString(2, annotation);
                preparedStatement.setString(3, status);
                preparedStatement.setInt(4, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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
                preparedStatement = con.prepareStatement("DELETE FROM " + dbName + ".activity WHERE id = ?");
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        } else {
            throw new DatabaseConnectionException();
        }
    }
}
