package com.kikimanjaro.calendar.activity.entity;

public class Activity implements IActivity {
    /**
     * This id is an int representing the identifier of this Activity,
     * it's automatically generated after the first entrance in database
     */
    private Integer id;
    /**
     * The date of the activity, this date is a timestamp from 1st of January, 1970
     */
    private long date;
    /**
     * This annotation is a String representing the comment inside of this Activity
     */
    private String annotation;
    /**
     * This status is an ActivityStatus representing the state of this Activity
     */
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

    /**
     * <p> Get the date of the Activity, this date is a timestamp from 1st of January, 1970 </p>
     *
     * @return The date of the Activity
     */
    @Override
    public long getDate() {
        return date;
    }

    /**
     * <p>
     * Get the annotation of the Activity, this annotation is a String representing the comment inside of this Activity
     * </p>
     *
     * @return The annotation of the Activity
     */
    @Override
    public String getAnnotation() {
        return annotation;
    }

    /**
     * <p>
     * Get the status of the Activity, this status is an ActivityStatus representing the state of this Activity
     * </p>
     *
     * @return The status of the Activity
     */
    @Override
    public ActivityStatus getStatus() {
        return status;
    }

    /**
     * <p>
     * Get the id of the Activity, this id is an int representing the identifier of this Activity,
     * it's automatically generated after the first entrance in database
     * </p>
     *
     * @return The id of the Activity
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * <p> Set the date of the Activity, this date is a timestamp from 1st of January, 1970 </p>
     *
     * @param date The timestamp of the Activity
     */
    @Override
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * <p>
     * Set the annotation of the Activity, this annotation is a String representing the comment inside of this Activity
     * </p>
     *
     * @param annotation The annotation of the Activity
     */
    @Override
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * <p>
     * Get the status of the Activity, this status is an ActivityStatus representing the state of this Activity
     * </p>
     *
     * @param status The status of the Activity
     */
    @Override
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
}
