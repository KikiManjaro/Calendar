package com.kikimanjaro.calendar.calendar.ui;

import com.kikimanjaro.calendar.activity.ui.ActivityManagerFrame;
import com.kikimanjaro.calendar.main.service.FrameService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;

import javax.swing.*;
import java.awt.*;

public class CalendarButtonPanel extends JPanel {

    protected ITimeService timeService;
    protected FrameService frameService;
    protected JButton leftYearButton;
    protected JButton leftMonthButton;
    protected JButton newActivityButton;
    protected JButton rightMonthButton;
    protected JButton rightYearButton;

    public CalendarButtonPanel() {
        timeService = TimeService.getInstance();
        frameService = FrameService.getInstance();
        this.setLayout(new GridBagLayout());
        initButtons();
    }

    private void initButtons() {
        leftYearButton = new JButton("<<<");
        leftYearButton.addActionListener(e -> {
            timeService.lastYear();
            frameService.refresh();
        });

        leftMonthButton = new JButton("<");
        leftMonthButton.addActionListener(e -> {
                    timeService.lastMonth();
                    frameService.refresh();
                }
        );

        newActivityButton = new JButton("Nouvelle Activité");
        newActivityButton.addActionListener(e -> new ActivityManagerFrame());

        rightMonthButton = new JButton(">");
        rightMonthButton.addActionListener(e -> {
            timeService.nextMonth();
            frameService.refresh();
        });

        rightYearButton = new JButton(">>>");
        rightYearButton.addActionListener(e -> {
            timeService.nextYear();
            frameService.refresh();
        });

        GridBagConstraints constraints = getGridBagConstraints();
        this.add(leftYearButton, constraints);
        constraints.gridx += 1;
        this.add(leftMonthButton, constraints);
        constraints.gridx += 1;
        this.add(newActivityButton, constraints);
        constraints.gridx += 1;
        this.add(rightMonthButton, constraints);
        constraints.gridx += 1;
        this.add(rightYearButton, constraints);
    }

    private GridBagConstraints getGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        return constraints;
    }
}
