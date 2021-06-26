package com.kikimanjaro.calendar.main.service;

import com.kikimanjaro.calendar.main.ui.MainFrame;

public interface IFrameService {
    void refresh();

    void registerMainFrame(MainFrame frame);

    MainFrame getMainFrame();
}
