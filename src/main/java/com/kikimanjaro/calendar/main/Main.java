package com.kikimanjaro.calendar.main;

import com.kikimanjaro.calendar.main.ui.MainFrame;

import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        new MainFrame();
    }
}
