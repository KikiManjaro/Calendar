package com.kikimanjaro.calendar.main.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeService implements ITimeService {
    private static TimeService instance;
    private final String[] dayOfWeek = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    private final String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
    protected LocalDate localDate;

    private TimeService() {
        localDate = new Date().toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
    }

    /**
     * <p>
     * Get or create a TimeService
     * </p>
     *
     * @return A TimeService
     */
    public static TimeService getInstance() {
        if (instance == null) {
            instance = new TimeService();
        }
        return instance;
    }

    /**
     * <p>Get the current Year</p>
     *
     * @return The current Year
     */
    @Override
    public int getCurrentYear() {
        return this.localDate.getYear();
    }

    /**
     * <p>Get the current Month from 1 to 12</p>
     *
     * @return The current Month
     */
    @Override
    public int getCurrentMonth() {
        return this.localDate.getMonthValue();
    }

    /**
     * <p>Get the length of the current Month, 28, 29, 30, 31</p>
     *
     * @return The lenght of the current Month
     */
    @Override
    public int getLengthOfMonth() {
        return this.localDate.lengthOfMonth();
    }

    /**
     * <p>Get the first day of the current Month, from 1 (Monday) to 7 (Sunday)</p>
     *
     * @return The first day of the current Month
     */
    @Override
    public int getFirstDayOfMonth() {
        return this.localDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    /**
     * <p>Get the String value of the day corresponding to the setted int, from 0 (Monday) to 6 (Sunday)</p>
     *
     * @param indexOfDay The index of the day in a week from 0 (Monday) to 6 (Sunday)
     * @return The String value of the day (in French)
     */
    @Override
    public String getDayOfWeekFromInt(int indexOfDay) {
        if (indexOfDay < 0) {
            indexOfDay += dayOfWeek.length;
            getDayOfWeekFromInt(indexOfDay);
        }
        return dayOfWeek[indexOfDay % dayOfWeek.length];
    }

    /**
     * <p>Get the String value of the current Month</p>
     *
     * @return The String value of the current Month
     */
    @Override
    public String getCurrentMonthToString() {
        return this.getMonthFromInt(this.getCurrentMonth() - 1);
    }

    /**
     * Remove one year from the LocalDate
     */
    @Override
    public void lastYear() {
        this.localDate = this.localDate.minusYears(1);
    }

    /**
     * Add one year to the LocalDate
     */
    @Override
    public void nextYear() {
        this.localDate = this.localDate.plusYears(1);
    }

    /**
     * Remove one month from the LocalDate
     */
    @Override
    public void lastMonth() {
        this.localDate = this.localDate.minusMonths(1);
    }

    /**
     * Add one month to the LocalDate
     */
    @Override
    public void nextMonth() {
        this.localDate = this.localDate.plusMonths(1);
    }

    /**
     * <p>Get the timestamp value of the setted day, month and year without hour, minute, second and millisecond</p>
     *
     * @param day   The day of the date to get timestamp from
     * @param month The month of the date to get timestamp from
     * @param year  The year of the date to get timestamp from
     * @return The timestamp value of the setted day, month and year
     */
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

    /**
     * <p>Get the timestamp value of the setted date without hour, minute, second and millisecond</p>
     *
     * @param date The date to get timestamp from
     * @return The timestamp value of the setted date
     */
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
