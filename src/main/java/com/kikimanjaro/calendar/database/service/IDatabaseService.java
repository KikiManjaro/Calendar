package com.kikimanjaro.calendar.database.service;

import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;

import java.util.List;

public interface IDatabaseService {

    List<IActivity> getActivitiesFromDate(long timestamp) throws DatabaseConnectionException;

    void registerActivity(long date, String annotation, String status) throws DatabaseConnectionException;

    void updateActivity(int id, long date, String annotation, String status) throws DatabaseConnectionException;

    void deleteActivity(int id) throws DatabaseConnectionException;
}
