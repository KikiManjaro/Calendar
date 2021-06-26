package com.kikimanjaro.calendar.activity.entity;

public interface IActivity {
    long getDate();

    String getAnnotation();

    ActivityStatus getStatus();

    Integer getId();

    void setDate(long date);

    void setAnnotation(String annotation);

    void setStatus(ActivityStatus status);
}
