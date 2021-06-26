package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.service.ActivityService;
import com.kikimanjaro.calendar.main.service.FrameService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.TimeZone;

class ActivityManagerPanelTest {

    private ActivityManagerFrame activityManagerFrame;
    private ActivityManagerPanel activityManagerPanel;
    private ITimeService timeService;
    private ActivityService activityService;
    private FrameService frameService;
    private Activity activity;

    @BeforeEach
    void setUp() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        activityManagerFrame = Mockito.mock(ActivityManagerFrame.class);
        activity = new Activity(891561600000L, "toto", ActivityStatus.TEST);
        activityManagerPanel = new ActivityManagerPanel(activityManagerFrame, activity);
        timeService = Mockito.mock(TimeService.class);
        activityService = Mockito.mock(ActivityService.class);
        frameService = Mockito.mock(FrameService.class);
        activityManagerPanel.timeService = timeService;
        activityManagerPanel.activityService = activityService;
        activityManagerPanel.frameService = frameService;
    }

    @Test
    void whenInitWithActivity_shouldDateModelHaveActivityDate() {
        Assertions.assertEquals(new Date(891561600000L), activityManagerPanel.dateModel.getValue());
    }

    @Test
    void whenInitWithActivity_shouldAnnotationTextFieldHaveActivityAnnotation() {
        Assertions.assertEquals("toto", activityManagerPanel.annotationTextField.getText());
    }

    @Test
    void whenInitWithActivity_shouldStatusComboBoxHaveActivityStatus() {
        Assertions.assertEquals(ActivityStatus.TEST.toString(), activityManagerPanel.statusComboBox.getSelectedItem().toString());
    }

    @Test
    void whenRightDeleteButtonActivated_shouldDeleteAndRefresh() {
        activityManagerPanel.deleteButton.doClick();
        Mockito.verify(activityService, Mockito.times(1)).deleteActivity(activity);
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

    @Test
    void whenRightValidateButtonActivated_shouldValidateAndRefresh() {
        activityManagerPanel.validateButton.doClick();
        Mockito.verify(activityService, Mockito.times(1)).updateActivity(activity);
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

    @Test
    void whenRegisterOrUpdateActivity_shouldUpdateIfActivityNotNull() {
        activityManagerPanel.registerOrUpdateActivity();
        Mockito.verify(activityService, Mockito.times(1)).updateActivity(activity);
    }

    @Test
    void whenRegisterOrUpdateActivity_shouldRegisterIfActivityNull() {
        activityManagerPanel.activity = null;
        activityManagerPanel.registerOrUpdateActivity();
        Mockito.verify(activityService, Mockito.times(1)).registerActivity(Mockito.any());
    }
}