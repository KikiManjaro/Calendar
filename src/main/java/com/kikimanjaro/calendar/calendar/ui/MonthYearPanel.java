package com.kikimanjaro.calendar.calendar.ui;

import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;

import javax.swing.*;
import java.awt.*;

public class MonthYearPanel extends JPanel {

    private final ITimeService timeService;

    public MonthYearPanel() {
        timeService = TimeService.getInstance();
        this.setLayout(new BorderLayout(8, 0));

        initLabel(timeService.getCurrentMonthToString(), 22, BorderLayout.WEST);
        initLabel(String.valueOf(timeService.getCurrentYear()), 30, BorderLayout.EAST);
    }

    private void initLabel(String text, int size, String position) {
        JLabel yearLabel = new JLabel(text);
        Font oldYearFont = yearLabel.getFont();
        yearLabel.setFont(new Font(oldYearFont.getName(), oldYearFont.getStyle(), size));
        this.add(yearLabel, position);
    }
}
