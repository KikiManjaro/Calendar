package com.kikimanjaro.calendar.calendar.ui;

import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.activity.service.ActivityService;
import com.kikimanjaro.calendar.activity.ui.ActivityPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DayPanel extends JPanel {
    private int day;
    private int month;
    private int year;

    public DayPanel(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        init();
        initActivities();
    }

    private void init() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        this.add(new JLabel(String.valueOf(day)), getGridBagConstraints(0, 0.1));
    }

    private void initActivities() {
        List<IActivity> activities = ActivityService.getInstance().findActivity(day, month, year);
        Container cont = new Container();
        cont.setLayout(new GridLayout(activities.size(), 1, 0, 2));
        for (IActivity activity : activities) {
            cont.add(new ActivityPanel(activity));
        }
        JScrollPane scrollPane = new JScrollPane(cont);
        this.add(scrollPane, getGridBagConstraints(1, 0.9));
    }

    private GridBagConstraints getGridBagConstraints(int gridy, double weigthy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.weightx = 1;
        constraints.weighty = weigthy;
        return constraints;
    }

}
