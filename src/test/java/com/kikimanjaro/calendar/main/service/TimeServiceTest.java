package com.kikimanjaro.calendar.main.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Date;

class TimeServiceTest {

    private TimeService timeService;

    @BeforeEach
    void setUp() {
        timeService = TimeService.getInstance();
        timeService.localDate = new Date(891561600000L).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //Date setted to Friday 03/04/1998 00:00:00 for testing purposes
    }

    @Test
    void whenGetCurrentYear_shouldReturnYearOfLocalDate() {
        Assertions.assertEquals(1998, timeService.getCurrentYear());
    }

    @Test
    void whenGetCurrentMonth_shouldReturnMonthOfLocalDate() {
        Assertions.assertEquals(4, timeService.getCurrentMonth());
    }

    @Test
    void whenGetLengthOfMonth_shouldReturnLengthOfMonthOfLocalDate() {
        Assertions.assertEquals(30, timeService.getLengthOfMonth());
        timeService.localDate = new Date(886464000000L).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Assertions.assertEquals(28, timeService.getLengthOfMonth());
    }

    @Test
    void whenGetCurrentMonthToString_shouldReturnMonthOfLocalDate() {
        Assertions.assertEquals("Avril", timeService.getCurrentMonthToString());
    }

    @Test
    void whenGetFirstDayOfMonth_shouldReturnFirstDayOfMonthOfLocalDate() { //Monday 1 / Sunday 7
        Assertions.assertEquals(3, timeService.getFirstDayOfMonth());
    }

    @Test
    void whenGetDayOfWeekFromInt_shouldReturnDayString() { //Lundi 0 / Dimanche 6
        Assertions.assertEquals("Lundi", timeService.getDayOfWeekFromInt(0));
        Assertions.assertEquals("Mardi", timeService.getDayOfWeekFromInt(1));
        Assertions.assertEquals("Mercredi", timeService.getDayOfWeekFromInt(2));
        Assertions.assertEquals("Jeudi", timeService.getDayOfWeekFromInt(3));
        Assertions.assertEquals("Vendredi", timeService.getDayOfWeekFromInt(4));
        Assertions.assertEquals("Samedi", timeService.getDayOfWeekFromInt(5));
        Assertions.assertEquals("Dimanche", timeService.getDayOfWeekFromInt(6));
    }

    @Test
    void whenGetDayOfWeekFromIntOutOfBound_shouldReturnModuloDayString() {
        Assertions.assertEquals("Mardi", timeService.getDayOfWeekFromInt(8));
        Assertions.assertEquals("Dimanche", timeService.getDayOfWeekFromInt(-1));
        Assertions.assertEquals("Vendredi", timeService.getDayOfWeekFromInt(32));
        Assertions.assertEquals("Lundi", timeService.getDayOfWeekFromInt(-7));
    }

    @Test
    void whenGetMonthFromInt_shouldReturnMonthString() { //Janvier 0 / Décembre 11
        Assertions.assertEquals("Janvier", timeService.getMonthFromInt(0));
        Assertions.assertEquals("Février", timeService.getMonthFromInt(1));
        Assertions.assertEquals("Mars", timeService.getMonthFromInt(2));
        Assertions.assertEquals("Avril", timeService.getMonthFromInt(3));
        Assertions.assertEquals("Mai", timeService.getMonthFromInt(4));
        Assertions.assertEquals("Juin", timeService.getMonthFromInt(5));
        Assertions.assertEquals("Juillet", timeService.getMonthFromInt(6));
        Assertions.assertEquals("Aout", timeService.getMonthFromInt(7));
        Assertions.assertEquals("Septembre", timeService.getMonthFromInt(8));
        Assertions.assertEquals("Octobre", timeService.getMonthFromInt(9));
        Assertions.assertEquals("Novembre", timeService.getMonthFromInt(10));
        Assertions.assertEquals("Décembre", timeService.getMonthFromInt(11));
    }

    @Test
    void whenGetMonthFromIntOutOfBound_shouldReturnModuloMonthString() {
        Assertions.assertEquals("Janvier", timeService.getMonthFromInt(12));
        Assertions.assertEquals("Décembre", timeService.getMonthFromInt(-1));
        Assertions.assertEquals("Octobre", timeService.getMonthFromInt(21));
        Assertions.assertEquals("Janvier", timeService.getMonthFromInt(-12));
    }

    @Test
    void whenLastYear_shouldRemoveYearFromLocalDate() {
        timeService.lastYear();
        Assertions.assertEquals(1997, timeService.getCurrentYear());
        timeService.lastYear();
        Assertions.assertEquals(1996, timeService.getCurrentYear());
    }

    @Test
    void whenNextYear_shouldAddYearToLocalDate() {
        timeService.nextYear();
        Assertions.assertEquals(1999, timeService.getCurrentYear());
        timeService.nextYear();
        Assertions.assertEquals(2000, timeService.getCurrentYear());
    }

    @Test
    void whenLastMonth_shouldRemoveMonthFromLocalDate() {
        timeService.lastMonth();
        Assertions.assertEquals(3, timeService.getCurrentMonth());
        timeService.lastMonth();
        Assertions.assertEquals(2, timeService.getCurrentMonth());
    }

    @Test
    void whenNextMonth_shouldAddMonthToLocalDate() {
        timeService.nextMonth();
        Assertions.assertEquals(5, timeService.getCurrentMonth());
        timeService.nextMonth();
        Assertions.assertEquals(6, timeService.getCurrentMonth());
    }


    @Test
    void whenGetTimestampFromDayMonthYear_shouldReturnTimestampOfDate() {
        Assertions.assertEquals(891561600000L, timeService.getTimestampFromDate(3, 4, 1998));
    }

    @Test
    void whenGetTimestampFromDate_shouldReturnTimestampOfDate() {
        Assertions.assertEquals(891561600000L, timeService.getTimestampFromDate(new Date(891561600000L)));
    }

    @Test
    void whenGetTimestampFromDate_shouldReturnTimestampOfDateRoundedToDay() {
        Assertions.assertEquals(891561600000L, timeService.getTimestampFromDate(new Date(891561601111L)));
    }
}