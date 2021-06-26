package com.kikimanjaro.calendar.main.service;

import com.kikimanjaro.calendar.main.ui.MainFrame;

import java.awt.*;

public interface IFrameService {
    void refresh();

    void registerMainFrame(MainFrame frame);

    MainFrame getMainFrame();

    GridBagConstraints getGridBagConstraints();
}
