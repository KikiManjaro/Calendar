package com.kikimanjaro.calendar.calendar.ui;

import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;

import javax.swing.*;
import java.awt.*;

public class CalendarPanel extends JPanel {

    private final transient ITimeService timeService;

    public CalendarPanel() {
        timeService = TimeService.getInstance();
        init();
        initDaysOfWeek();
        initDays();
    }

    private void init() {
        this.setLayout(new GridLayout(0, 7, 4, 5));
    }

    private void initDays() {
        int firstDayOfMonth = timeService.getFirstDayOfMonth();
        int currentMonth = timeService.getCurrentMonth();
        int currentYear = timeService.getCurrentYear();

        for (int i = 0; i < firstDayOfMonth - 1; i++) {
            this.add(new Container());
        }
        for (int day = 1; day <= timeService.getLengthOfMonth(); day++) {
            this.add(new DayPanel(day, currentMonth, currentYear));
        }
    }

    private void initDaysOfWeek() {
        for (int i = 0; i < 7; i++) {
            this.add(new JLabel(timeService.getDayOfWeekFromInt(i), SwingConstants.CENTER));
        }
    }
}
