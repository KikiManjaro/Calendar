package com.kikimanjaro.calendar.activity.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class DateLabelFormatterTest {

    private DateLabelFormatter dateLabelFormatter;
    private Calendar cal;

    @BeforeEach
    void setUp() {
        dateLabelFormatter = new DateLabelFormatter();
        cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("GMT")));
        cal.set(1998, Calendar.APRIL, 3);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

    }

    @Test
    void whenStringToValue_shouldReturnFormatedDate() {
        Assertions.assertEquals("03-04-1998", dateLabelFormatter.valueToString(cal));
    }

    @Test
    void whenValueToString_shouldReturnRightDate() throws ParseException {
        Object date = dateLabelFormatter.stringToValue("03-04-1998");
        Assertions.assertTrue(date instanceof Date);
        Calendar newCal = Calendar.getInstance();
        newCal.setTime((Date) date);
        Assertions.assertEquals(1998, newCal.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.APRIL, newCal.get(Calendar.MONTH));
        Assertions.assertEquals(3, newCal.get(Calendar.DAY_OF_MONTH));
    }
}