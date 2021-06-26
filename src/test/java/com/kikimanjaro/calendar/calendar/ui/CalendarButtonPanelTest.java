package com.kikimanjaro.calendar.calendar.ui;

import com.kikimanjaro.calendar.main.service.FrameService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CalendarButtonPanelTest {

    private CalendarButtonPanel calendarButtonPanel;
    private ITimeService timeService;
    private FrameService frameService;

    @BeforeEach
    void setUp() {
        calendarButtonPanel = new CalendarButtonPanel();
        timeService = Mockito.mock(TimeService.class);
        frameService = Mockito.mock(FrameService.class);
        calendarButtonPanel.timeService = timeService;
        calendarButtonPanel.frameService = frameService;
    }

    @Test
    void whenLeftYearButtonActivated_shouldLastYearAndRefresh() {
        calendarButtonPanel.leftYearButton.doClick();
        Mockito.verify(timeService, Mockito.times(1)).lastYear();
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

    @Test
    void whenLeftMonthButtonActivated_shouldLastMonthAndRefresh() {
        calendarButtonPanel.leftMonthButton.doClick();
        Mockito.verify(timeService, Mockito.times(1)).lastMonth();
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

    @Test
    void whenRightYearButtonActivated_shouldLNextYearAndRefresh() {
        calendarButtonPanel.rightYearButton.doClick();
        Mockito.verify(timeService, Mockito.times(1)).nextYear();
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

    @Test
    void whenRightMonthButtonActivated_shouldNextMonthAndRefresh() {
        calendarButtonPanel.rightMonthButton.doClick();
        Mockito.verify(timeService, Mockito.times(1)).nextMonth();
        Mockito.verify(frameService, Mockito.times(1)).refresh();
    }

}