package com.kikimanjaro.calendar.activity.entity;

public class Activity implements IActivity {
    private Integer id;
    private long date;
    private String annotation;
    private ActivityStatus status;

    public Activity(long date, String annotation, ActivityStatus status) {
        this.date = date;
        this.annotation = annotation;
        this.status = status;
    }

    public Activity(int id, long date, String annotation, ActivityStatus status) {
        this.id = id;
        this.date = date;
        this.annotation = annotation;
        this.status = status;
    }

    @Override
    public long getDate() {
        return date;
    }

    @Override
    public String getAnnotation() {
        return annotation;
    }

    @Override
    public ActivityStatus getStatus() {
        return status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
}
