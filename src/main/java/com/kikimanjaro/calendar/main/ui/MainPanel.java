package com.kikimanjaro.calendar.main.ui;

import com.kikimanjaro.calendar.calendar.ui.CalendarPanel;
import com.kikimanjaro.calendar.calendar.ui.TopPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        this.setLayout(new BorderLayout());
        initTopPanel();
        initCalendarPanel();
    }

    private void initTopPanel() {
        TopPanel topPanel = new TopPanel();
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void initCalendarPanel() {
        CalendarPanel calendarPanel = new CalendarPanel();
        this.add(calendarPanel, BorderLayout.CENTER);
    }
}
