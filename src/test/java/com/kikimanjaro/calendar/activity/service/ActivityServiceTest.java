package com.kikimanjaro.calendar.activity.service;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;
import com.kikimanjaro.calendar.database.service.DatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.List;

class ActivityServiceTest {

    private ActivityService activityService;
    private Activity testActivity;

    @BeforeEach
    void setUp() {
        activityService = ActivityService.getInstance();
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        testActivity = Mockito.mock(Activity.class);
        Mockito.when(testActivity.getId()).thenReturn(1);
        Mockito.when(testActivity.getDate()).thenReturn(123456L);
        Mockito.when(testActivity.getAnnotation()).thenReturn("toto");
        Mockito.when(testActivity.getStatus()).thenReturn(ActivityStatus.TEST);
    }

    @Test
    void whenRegisterActivity_shouldAskDatabaseToRegister() throws DatabaseConnectionException {
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        activityService.registerActivity(testActivity);
        Mockito.verify(databaseService, Mockito.times(1)).registerActivity(testActivity.getDate(),
                testActivity.getAnnotation(), testActivity.getStatus().toString());
    }

    @Test
    void whenUpdateActivity_shouldAskDatabaseToUpdate() throws DatabaseConnectionException {
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        activityService.updateActivity(testActivity);
        Mockito.verify(databaseService, Mockito.times(1)).updateActivity(Mockito.anyInt(),
                Mockito.anyLong(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void whenUpdateActivity_shouldAskDatabaseToDelete() throws DatabaseConnectionException {
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        activityService.deleteActivity(testActivity);
        Mockito.verify(databaseService, Mockito.times(1)).deleteActivity(Mockito.anyInt());
    }

    @Test
    void whenFindActivities_shouldAskDatabaseToFind() throws DatabaseConnectionException {
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        activityService.findActivity(1, 1, 2021);
        Mockito.verify(databaseService, Mockito.times(1)).getActivitiesFromDate(Mockito.anyLong());
    }

    @Test
    void whenFindActivitiesGetDatabaseConnectionException_shouldReturnEmptyArrayList() throws DatabaseConnectionException {
        DatabaseService databaseService = Mockito.mock(DatabaseService.class);
        activityService.databaseService = databaseService;
        Mockito.when(databaseService.getActivitiesFromDate(Mockito.anyLong())).thenThrow(new DatabaseConnectionException());
        List<IActivity> activity = activityService.findActivity(1, 1, 2021);
        Assertions.assertNotNull(activity);
        Assertions.assertTrue(activity.isEmpty());
    }

    @Test
    void whenGetColorFromPendingStatus_shouldReturnGray() {
        Assertions.assertEquals(Color.gray, activityService.getColorFromStatus(ActivityStatus.PENDING));
    }

    @Test
    void whenGetColorFromInProgressStatus_shouldReturnCyan() {
        Assertions.assertEquals(Color.cyan, activityService.getColorFromStatus(ActivityStatus.IN_PROGRESS));
    }

    @Test
    void whenGetColorFromTestStatus_shouldReturnBlue() {
        Assertions.assertEquals(Color.blue, activityService.getColorFromStatus(ActivityStatus.TEST));
    }

    @Test
    void whenGetColorFromDoneStatus_shouldReturnGreen() {
        Assertions.assertEquals(Color.green, activityService.getColorFromStatus(ActivityStatus.DONE));
    }

    @Test
    void whenGetColorFromDefaultStatus_shouldReturnWhite() {
        Assertions.assertEquals(Color.white, activityService.getColorFromStatus(ActivityStatus.NOT_DEFINED));
    }

}