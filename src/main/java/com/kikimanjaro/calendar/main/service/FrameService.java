package com.kikimanjaro.calendar.main.service;

import com.kikimanjaro.calendar.main.ui.MainFrame;

import java.awt.*;

public class FrameService implements IFrameService {

    private static FrameService instance;
    protected MainFrame mainFrame;

    /**
     * <p>
     * Get or create a FrameService
     * </p>
     *
     * @return A FrameService
     */
    public static FrameService getInstance() {
        if (instance == null) {
            instance = new FrameService();
        }
        return instance;
    }

    /**
     * <p>Refresh the MainFrame</p>
     */
    @Override
    public void refresh() {
        this.mainFrame.refresh();
    }

    /**
     * <p>Register the MainFrame in this service</p>
     *
     * @param frame The MainFrame to register
     */
    @Override
    public void registerMainFrame(MainFrame frame) {
        this.mainFrame = frame;
    }

    /**
     * <p>Get the MainFrame registered in this service</p>
     *
     * @return The MainFrame registered in this service
     */
    @Override
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * <p>Get the most used GridBagConstraints</p>
     *
     * @return The default GridBagConstraints
     */
    public GridBagConstraints getGridBagConstraints() {
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
