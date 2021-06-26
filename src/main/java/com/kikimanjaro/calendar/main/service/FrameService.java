package com.kikimanjaro.calendar.main.service;

import com.kikimanjaro.calendar.main.ui.MainFrame;

public class FrameService implements IFrameService {

    private static FrameService instance;
    protected MainFrame mainFrame;

    public static FrameService getInstance() {
        if (instance == null) {
            instance = new FrameService();
        }
        return instance;
    }

    @Override
    public void refresh() {
        this.mainFrame.refresh();
    }

    @Override
    public void registerMainFrame(MainFrame frame) {
        this.mainFrame = frame;
    }

    @Override
    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
