package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityManagerFrameTest {

    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity(891561600000L, "toto", ActivityStatus.TEST);
    }

    @Test
    void whenInit_shouldCreateActivityManagerPanel() {
        ActivityManagerFrame activityManagerFrame = new ActivityManagerFrame(false);
        Assertions.assertNotNull(activityManagerFrame.contentPane);
    }

    @Test
    void whenInit_shouldCreateActivityManagerPanelWithoutActivity() {
        ActivityManagerFrame activityManagerFrame = new ActivityManagerFrame(false);
        ActivityManagerPanel contentPane = activityManagerFrame.contentPane;
        Assertions.assertNull(contentPane.activity);
    }

    @Test
    void whenInitWithActivity_shouldCreateActivityManagerPanel() {
        ActivityManagerFrame activityManagerFrame = new ActivityManagerFrame(false, activity);
        Assertions.assertNotNull(activityManagerFrame.contentPane);
    }

    @Test
    void whenInitWithActivity_shouldCreateActivityManagerPanelWithoutActivity() {
        ActivityManagerFrame activityManagerFrame = new ActivityManagerFrame(false, activity);
        ActivityManagerPanel contentPane = activityManagerFrame.contentPane;
        Assertions.assertNotNull(contentPane.activity);
    }
}