package com.kikimanjaro.calendar.calendar.ui;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        this.setLayout(new BorderLayout());
        initMonthYearPanel();
        initCalendarButtonPanel();
    }

    private void initMonthYearPanel() {
        MonthYearPanel monthYearPanel = new MonthYearPanel();
        this.add(monthYearPanel, BorderLayout.WEST);
    }

    private void initCalendarButtonPanel() {
        CalendarButtonPanel calendarButtonPanel = new CalendarButtonPanel();
        this.add(calendarButtonPanel, BorderLayout.EAST);
    }
}
