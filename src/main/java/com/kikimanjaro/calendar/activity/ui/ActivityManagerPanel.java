package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.Activity;
import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.activity.service.ActivityService;
import com.kikimanjaro.calendar.activity.util.DateLabelFormatter;
import com.kikimanjaro.calendar.main.service.FrameService;
import com.kikimanjaro.calendar.main.service.ITimeService;
import com.kikimanjaro.calendar.main.service.TimeService;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Properties;

public class ActivityManagerPanel extends JPanel {

    protected ActivityService activityService;
    protected FrameService frameService;
    protected ITimeService timeService;
    private GridBagConstraints constraints;
    protected JLabel dateLabel;
    protected JDatePicker datePicker;
    protected JLabel annotationLabel;
    protected JTextArea annotationTextField;
    protected JLabel statusLabel;
    protected JComboBox<ActivityStatus> statusComboBox;

    protected JButton cancelButton;
    protected JButton validateButton;
    protected JButton deleteButton;
    protected Dialog frame;
    protected UtilDateModel dateModel;
    protected IActivity activity;
    protected JPanel buttonPanel;

    public ActivityManagerPanel(Dialog frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        this.constraints = createGridBagConstraints();

        activityService = ActivityService.getInstance();
        frameService = FrameService.getInstance();
        timeService = TimeService.getInstance();

        initPanel();
    }

    public ActivityManagerPanel(ActivityManagerFrame frame, IActivity activity) {
        this(frame);
        this.activity = activity;
        fullfillFields();
        initDeleteButton();
    }

    private void fullfillFields() {
        dateModel.setValue(new Date(this.activity.getDate()));
        annotationTextField.setText(this.activity.getAnnotation());
        statusComboBox.getModel().setSelectedItem(this.activity.getStatus());
    }

    private void initPanel() {
        initDate();
        initAnnotation();
        initStatus();
        initButtons();
    }

    private void initDeleteButton() {
        if (this.activity != null) {
            this.deleteButton = new JButton("Supprimer");
            this.deleteButton.addActionListener(e -> {
                activityService.deleteActivity(this.activity);
                frameService.refresh();
                frame.dispose();
            });
            constraints.gridx += 1;
            this.buttonPanel.add(deleteButton, constraints);
        }
    }

    private void initButtons() {
        initButtonPanel();

        this.cancelButton = new JButton("Annuler");
        this.cancelButton.addActionListener(e -> frame.dispose());
        buttonPanel.add(cancelButton);

        this.validateButton = new JButton("Valider");
        this.validateButton.addActionListener(e -> {
            registerOrUpdateActivity();
            frameService.refresh();
            frame.dispose();
        });
        buttonPanel.add(validateButton);
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        GridLayout layout = new GridLayout();
        layout.setHgap(6);
        buttonPanel.setLayout(layout);
        constraints.gridy += 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.CENTER;
        this.add(buttonPanel, constraints);
    }

    private void initStatus() {
        constraints.weighty = 0.2;
        this.statusLabel = new JLabel("Status");
        constraints.gridy += 1;
        constraints.gridx = 0;
        this.add(statusLabel, constraints);
        this.statusComboBox = new JComboBox<>(new ActivityStatus[]{ActivityStatus.PENDING, ActivityStatus.IN_PROGRESS, ActivityStatus.TEST, ActivityStatus.DONE
        });
        constraints.gridx += 1;
        this.add(statusComboBox, constraints);
    }

    private void initAnnotation() {
        constraints.weighty = 1;
        this.annotationLabel = new JLabel("Commentaire");
        constraints.gridy += 1;
        constraints.gridx = 0;
        this.add(annotationLabel, constraints);
        this.annotationTextField = new JTextArea();
        JScrollPane annotationScrollPane = new JScrollPane(annotationTextField);
        constraints.gridx += 1;
        this.add(annotationScrollPane, constraints);
    }

    private void initDate() {
        this.dateLabel = new JLabel("Date");
        this.add(dateLabel, constraints);
        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        constraints.gridx += 1;
        this.add(datePanel, constraints);
    }

    protected void registerOrUpdateActivity() {
        long timestamp = timeService.getTimestampFromDate(dateModel.getValue());
        if (this.activity != null) {
            this.activity.setDate(timestamp);
            this.activity.setAnnotation(annotationTextField.getText());
            this.activity.setStatus((ActivityStatus) statusComboBox.getSelectedItem());
            activityService.updateActivity(activity);
        } else {
            IActivity activity = new Activity(timestamp, annotationTextField.getText(), (ActivityStatus) statusComboBox.getSelectedItem());
            activityService.registerActivity(activity);
        }
    }

    private GridBagConstraints createGridBagConstraints() {
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
