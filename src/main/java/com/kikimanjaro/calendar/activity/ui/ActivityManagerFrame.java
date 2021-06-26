package com.kikimanjaro.calendar.activity.ui;

import com.kikimanjaro.calendar.activity.entity.IActivity;
import com.kikimanjaro.calendar.main.service.FrameService;

import javax.swing.*;
import java.awt.*;

public class ActivityManagerFrame extends JDialog {

    protected ActivityManagerPanel contentPane;

    /**
     * <p>Constructor</p>
     *
     * @param modal A boolean to set the window modal or not
     */
    public ActivityManagerFrame(boolean modal) {
        super(FrameService.getInstance().getMainFrame());
        setModal(modal);
        this.setTitle("Création d'activité");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(310, 360));
        this.setMinimumSize(new Dimension(310, 360));
        contentPane = new ActivityManagerPanel(this);
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /**
     * <p>Constructor</p>
     *
     * @param modal    A boolean to set the window modal or not
     * @param activity An Activity already registered to manage
     */
    public ActivityManagerFrame(boolean modal, IActivity activity) {
        super(FrameService.getInstance().getMainFrame());
        setModal(modal);
        this.setTitle("Création d'activité");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(310, 360));
        this.setMinimumSize(new Dimension(310, 360));
        contentPane = new ActivityManagerPanel(this, activity);
        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
