package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityPanelTest {

    private Activity activity;
    private ActivityPanel activityPanel;

    @BeforeEach
    void setUp() {
        activity = new Activity(891561600000L, "toto", ActivityStatus.TEST);
        activityPanel = new ActivityPanel(activity);
    }

    @Test
    void whenInit_shouldAnnotationLabelContainActivityAnnotation() {
        Assertions.assertEquals("toto", activityPanel.annotationLabel.getText());
    }
}