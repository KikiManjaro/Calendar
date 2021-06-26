package com.kikimanjaro.calendar.main.service;

import java.util.Date;

public interface ITimeService {

    int getCurrentYear();

    int getCurrentMonth();

    int getLengthOfMonth();

    int getFirstDayOfMonth();

    String getDayOfWeekFromInt(int indexOfDay);

    String getCurrentMonthToString();

    void lastYear();

    void nextYear();

    void lastMonth();

    void nextMonth();

    long getTimestampFromDate(int day, int month, int year);

    long getTimestampFromDate(Date date);
}
