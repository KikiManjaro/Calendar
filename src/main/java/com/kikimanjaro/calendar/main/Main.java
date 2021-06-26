package com.kikimanjaro.calendar.main;

import com.kikimanjaro.calendar.main.ui.MainFrame;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

public class Main {
    protected static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        log.info("Running application");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        new MainFrame();
    }
}
