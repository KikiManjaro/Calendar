package com.kikimanjaro.calendar.activity.service;

import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;
import com.kikimanjaro.calendar.database.service.DatabaseService;
import com.kikimanjaro.calendar.database.service.IDatabaseService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityService implements IActivityService {

    protected static Logger log = LoggerFactory.getLogger(ActivityService.class);
    static ActivityService instance;
    private final ITimeService timeService;
    protected IDatabaseService databaseService;

    private ActivityService() {
        databaseService = DatabaseService.getInstance();
        timeService = TimeService.getInstance();
    }

    /**
     * <p>
     * Get or create an ActivityService
     * </p>
     *
     * @return An ActivityService
     */
    public static ActivityService getInstance() {
        if (ActivityService.instance == null) {
            ActivityService.instance = new ActivityService();
        }
        return ActivityService.instance;
    }

    /**
     * <p>
     * Look for Activities with date correspond to day, month and year
     * </p>
     *
     * @param day   The day of the month of the Activity
     * @param month The month of the year of the Activity
     * @param year  The year of the Activity
     * @return A List of Activities of which date correspond to day, month and year
     */
    @Override
    public List<IActivity> findActivity(int day, int month, int year) {
        try {
            return databaseService.getActivitiesFromDate(timeService.getTimestampFromDate(day, month, year));
        } catch (DatabaseConnectionException e) {
            log.error("Can't find activities", e);
            return new ArrayList<>();
        }
    }

    /**
     * <p>
     * Register an Activity in database
     * </p>
     *
     * @param activity The Activity to register in database
     */
    @Override
    public void registerActivity(IActivity activity) {
        try {
            databaseService.registerActivity(activity.getDate(), activity.getAnnotation(), activity.getStatus().toString());
        } catch (DatabaseConnectionException e) {
            log.error("Can't register activity", e);
        }
    }

    /**
     * <p>
     * Update an Activity in database
     * </p>
     *
     * @param activity The Activity to update in database
     */
    @Override
    public void updateActivity(IActivity activity) {
        try {
            databaseService.updateActivity(activity.getId(), activity.getDate(), activity.getAnnotation(), activity.getStatus().toString());
        } catch (DatabaseConnectionException e) {
            log.error("Can't update activity", e);

        }
    }

    /**
     * <p>
     * Delete an Activity from database
     * </p>
     *
     * @param activity The Activity to delete from database
     */
    @Override
    public void deleteActivity(IActivity activity) {
        try {
            databaseService.deleteActivity(activity.getId());
        } catch (DatabaseConnectionException e) {
            log.error("Can't delete activity", e);
        }
    }

    /**
     * <p>
     * Get the color of an ActivityStatus
     * </p>
     *
     * @param status The ActivityStatus associated with a color
     * @return The Color corresponding to the ActivityStatus
     */
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
