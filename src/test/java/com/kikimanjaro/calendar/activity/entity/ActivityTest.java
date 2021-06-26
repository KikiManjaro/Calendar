package com.kikimanjaro.calendar.activity.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityTest {

    private Activity activity;
    private Activity activitywid;

    @BeforeEach
    void setUp() {
        activity = new Activity(1, 891561600, "toto", ActivityStatus.TEST);
        activitywid = new Activity(891561600, "toto", ActivityStatus.TEST);
    }

    @Test
    void whenCreateActivity_shouldGetFieldsReturnSettedFields() {
        Assertions.assertEquals(1, activity.getId());
        Assertions.assertEquals(891561600, activity.getDate());
        Assertions.assertEquals("toto", activity.getAnnotation());
        Assertions.assertEquals(ActivityStatus.TEST, activity.getStatus());
    }

    @Test
    void whenCreateActivityWithoutId_shouldGetFieldsReturnSettedFields() {
        Assertions.assertEquals(891561600, activitywid.getDate());
        Assertions.assertEquals("toto", activitywid.getAnnotation());
        Assertions.assertEquals(ActivityStatus.TEST, activitywid.getStatus());
    }

    @Test
    void whenCreateActivityWithoutId_shouldIdBeNull() {
        Assertions.assertNull(activitywid.getId());
    }

    @Test
    void whenSetDate_shouldDateBeChanged() {
        activity.setDate(123);
        Assertions.assertEquals(123, activity.getDate());
    }

    @Test
    void whenSetAnnotation_shouldAnnotationBeChanged() {
        activity.setAnnotation("tata");
        Assertions.assertEquals("tata", activity.getAnnotation());
    }

    @Test
    void whenSetStatus_shouldStatusBeChanged() {
        activity.setStatus(ActivityStatus.DONE);
        Assertions.assertEquals(ActivityStatus.DONE, activity.getStatus());
    }
}