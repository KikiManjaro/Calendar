package com.kikimanjaro.calendar.main.service;

import com.kikimanjaro.calendar.main.ui.MainFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FrameServiceTest {

    private FrameService frameService;

    @BeforeEach
    void setUp() {
        frameService = FrameService.getInstance();
    }

    @Test
    void whenNotRegisteredMainFrame_shouldMainFrameBeNull() {
        Assertions.assertNull(frameService.getMainFrame());
    }

    @Test
    void whenRegisterMainFrame_shouldMainFrameBeRegistered() {
        MainFrame frame = new MainFrame();
        frameService.registerMainFrame(frame);
        Assertions.assertEquals(frame, frameService.getMainFrame());
    }

    @Test
    void whenRefreshMainFrame_shouldAskMainFrameToRefresh() {
        MainFrame frame = Mockito.mock(MainFrame.class);
        frameService.registerMainFrame(frame);
        frameService.refresh();
        Mockito.verify(frame, Mockito.times(1)).refresh();
    }
}