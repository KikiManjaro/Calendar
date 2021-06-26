package com.kikimanjaro.calendar.main.ui;

import com.kikimanjaro.calendar.main.service.FrameService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final FrameService frameService;

    public MainFrame() {
        this.setTitle("Calendar");
        this.setPreferredSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(720, 480));
        this.setContentPane(new MainPanel());
        this.setLocationRelativeTo(null);
        pack();
        setVisible(true);
        frameService = FrameService.getInstance();
        frameService.registerMainFrame(this);
    }

    public void refresh() {
        this.setContentPane(new MainPanel());
        this.repaint();
        this.revalidate();
    }
}
