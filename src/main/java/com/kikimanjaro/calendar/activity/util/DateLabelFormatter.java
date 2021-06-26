package com.kikimanjaro.calendar.activity.util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "dd-MM-yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    /**
     * <p>Parses text returning an arbitrary Object. Some formatters may return null</p>
     *
     * @param text String to convert
     * @return Object representation of text
     * @throws ParseException if there is an error in the conversion
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    /**
     * <p>Returns the string value to display for value.</p>
     *
     * @param value Value to convert
     * @return String representation of value
     */
    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
