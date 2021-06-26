package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.activity.service.ActivityService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ActivityPanel extends JPanel {
    protected JLabel annotationLabel;
    protected transient IActivity activity;

    public ActivityPanel(IActivity activity) {
        this.activity = activity;
        this.setLayout(new BorderLayout());
        this.setBackground(ActivityService.getInstance().getColorFromStatus(activity.getStatus()));
        this.annotationLabel = new JLabel(activity.getAnnotation());
        this.add(annotationLabel, BorderLayout.NORTH);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ActivityManagerFrame(true, activity);
            }
        });
    }
}
