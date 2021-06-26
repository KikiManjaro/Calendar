package com.kikimanjaro.calendar.activity.entity;

public interface IActivity {
    long getDate();

    void setDate(long date);

    String getAnnotation();

    void setAnnotation(String annotation);

    ActivityStatus getStatus();

    void setStatus(ActivityStatus status);

    Integer getId();
}
