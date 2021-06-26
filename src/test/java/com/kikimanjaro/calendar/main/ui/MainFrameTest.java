package com.kikimanjaro.calendar.main.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class MainFrameTest {

    private MainFrame mainFrame;

    @BeforeEach
    void setUp() {
        mainFrame = new MainFrame();
    }

    @Test
    void whenRefresh_shouldReplaceContentOfFrame() {
        Container lastContentPane = mainFrame.getContentPane();
        mainFrame.refresh();
        Assertions.assertNotEquals(lastContentPane, mainFrame.getContentPane());
    }
}