package com.kikimanjaro.calendar.main.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeService implements ITimeService {
    private static TimeService instance;
    protected LocalDate localDate;
    private final String[] dayOfWeek = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    private final String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};

    private TimeService() {
        localDate = new Date().toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
    }

    public static TimeService getInstance() {
        if (instance == null) {
            instance = new TimeService();
        }
        return instance;
    }

    @Override
    public int getCurrentYear() {
        return this.localDate.getYear();
    }

    @Override
    public int getCurrentMonth() {
        return this.localDate.getMonthValue();
    }

    @Override
    public int getLengthOfMonth() {
        return this.localDate.lengthOfMonth();
    }

    @Override
    public int getFirstDayOfMonth() {
        return this.localDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    @Override
    public String getDayOfWeekFromInt(int indexOfDay) {
        if (indexOfDay < 0) {
            indexOfDay += dayOfWeek.length;
            getDayOfWeekFromInt(indexOfDay);
        }
        return dayOfWeek[indexOfDay % dayOfWeek.length];
    }

    @Override
    public String getCurrentMonthToString() {
        return this.getMonthFromInt(this.getCurrentMonth() - 1);
    }

    @Override
    public void lastYear() {
        this.localDate = this.localDate.minusYears(1);
    }

    @Override
    public void nextYear() {
        this.localDate = this.localDate.plusYears(1);
    }

    @Override
    public void lastMonth() {
        this.localDate = this.localDate.minusMonths(1);
    }

    @Override
    public void nextMonth() {
        this.localDate = this.localDate.plusMonths(1);
    }

    @Override
    public long getTimestampFromDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("GMT")));
        cal.set(year, month - 1, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    @Override
    public long getTimestampFromDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("GMT")));
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    protected String getMonthFromInt(int indexOfMonth) {
        if (indexOfMonth < 0) {
            indexOfMonth += months.length;
            getMonthFromInt(indexOfMonth);
        }
        return months[indexOfMonth % months.length];
    }
}
