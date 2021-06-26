package com.kikimanjaro.calendar.activity.service;

import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;
import com.kikimanjaro.calendar.database.service.DatabaseService;
import com.kikimanjaro.calendar.database.service.IDatabaseService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityService implements IActivityService {

    private final ITimeService timeService;
    protected IDatabaseService databaseService;
    static ActivityService instance;

    private ActivityService() {
        databaseService = DatabaseService.getInstance();
        timeService = TimeService.getInstance();
    }

    public static ActivityService getInstance() {
        if (ActivityService.instance == null) {
            ActivityService.instance = new ActivityService();
        }
        return ActivityService.instance;
    }

    @Override
    public List<IActivity> findActivity(int day, int month, int year) {
        try {
            return databaseService.getActivitiesFromDate(timeService.getTimestampFromDate(day, month, year));
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void registerActivity(IActivity activity) {
        try {
            databaseService.registerActivity(activity.getDate(), activity.getAnnotation(), activity.getStatus().toString());
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActivity(IActivity activity) {
        try {
            databaseService.updateActivity(activity.getId(), activity.getDate(), activity.getAnnotation(), activity.getStatus().toString());
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActivity(IActivity activity) {
        try {
            databaseService.deleteActivity(activity.getId());
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Color getColorFromStatus(ActivityStatus status) {
        switch (status) {
            case PENDING:
                return Color.gray;
            case IN_PROGRESS:
                return Color.cyan;
            case TEST:
                return Color.blue;
            case DONE:
                return Color.green;
            default:
                return Color.white;
        }
    }
}
