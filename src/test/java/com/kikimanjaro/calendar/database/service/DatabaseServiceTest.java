package com.kikimanjaro.calendar.database.service;

import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.database.exception.DatabaseConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//This test is using an external database to work properly, in case of error try to clear the test database
class DatabaseServiceTest {

    private DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        databaseService = DatabaseService.getInstance();
        databaseService.propertiesFileName = "jdbc-test.properties";
        databaseService.connect();
    }

    @Test
    void whenConnectWithRightCredentials_shouldConnectedBeTrue() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.connect();
        Assertions.assertTrue(databaseService.connected);
    }

    @Test
    void whenConnectWithWrongCredentials_shouldConnectedBeFalse() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.propertiesFileName = "jdbc-test-wrongpassword.properties";
        databaseService.connect();
        Assertions.assertFalse(databaseService.connected);
    }

    @Test
    void whenNotConnectedAndGetActivity_shouldThrowDatabaseConnectionException() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.connected = false;
        Assertions.assertThrows(DatabaseConnectionException.class, () -> databaseService.getActivitiesFromDate(123));
    }

    @Test
    void whenNotConnectedAndRegisterActivity_shouldThrowDatabaseConnectionException() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.connected = false;
        Assertions.assertThrows(DatabaseConnectionException.class, () -> databaseService.registerActivity(123, "toto", ActivityStatus.TEST.toString()));
    }

    @Test
    void whenNotConnectedAndUpdateActivity_shouldThrowDatabaseConnectionException() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.connected = false;
        Assertions.assertThrows(DatabaseConnectionException.class, () -> databaseService.updateActivity(1, 123, "toto", ActivityStatus.TEST.toString()));
    }

    @Test
    void whenNotConnectedAndDeleteActivity_shouldThrowDatabaseConnectionException() {
        DatabaseService databaseService = DatabaseService.getInstance();
        databaseService.connected = false;
        Assertions.assertThrows(DatabaseConnectionException.class, () -> databaseService.deleteActivity(1));
    }

    @Test
    void whenRegisterActivity_shouldFindActivityReturnSameActivity() throws DatabaseConnectionException {
        databaseService.registerActivity(123456, "toto", ActivityStatus.TEST.toString());
        List<IActivity> activitiesFromDate = databaseService.getActivitiesFromDate(123456);
        Assertions.assertNotNull(activitiesFromDate);
        Assertions.assertEquals(1, activitiesFromDate.size());
        Assertions.assertEquals("toto", activitiesFromDate.get(0).getAnnotation());
        Assertions.assertEquals(ActivityStatus.TEST, activitiesFromDate.get(0).getStatus());
        Assertions.assertEquals(123456, activitiesFromDate.get(0).getDate());
        databaseService.deleteActivity(activitiesFromDate.get(0).getId());
    }

    @Test
    void whenUpdateActivity_shouldUpdateActivity() throws DatabaseConnectionException {
        databaseService.registerActivity(123456, "toto", ActivityStatus.TEST.toString());
        List<IActivity> activitiesFromDate = databaseService.getActivitiesFromDate(123456);
        databaseService.updateActivity(activitiesFromDate.get(0).getId(), 654321, "tata", ActivityStatus.DONE.toString());
        activitiesFromDate = databaseService.getActivitiesFromDate(123456);
        Assertions.assertEquals(0, activitiesFromDate.size());
        activitiesFromDate = databaseService.getActivitiesFromDate(654321);
        Assertions.assertNotNull(activitiesFromDate);
        Assertions.assertEquals(1, activitiesFromDate.size());
        Assertions.assertEquals("tata", activitiesFromDate.get(0).getAnnotation());
        Assertions.assertEquals(ActivityStatus.DONE, activitiesFromDate.get(0).getStatus());
        Assertions.assertEquals(654321, activitiesFromDate.get(0).getDate());
        databaseService.deleteActivity(activitiesFromDate.get(0).getId());
    }

    @Test
    void whenDeleteActivity_FindActivityReturnEmptyList() throws DatabaseConnectionException {
        databaseService.registerActivity(123456, "toto", ActivityStatus.TEST.toString());
        List<IActivity> activitiesFromDate = databaseService.getActivitiesFromDate(123456);
        databaseService.deleteActivity(activitiesFromDate.get(0).getId());
        activitiesFromDate = databaseService.getActivitiesFromDate(123456);
        Assertions.assertEquals(0, activitiesFromDate.size());
    }
}