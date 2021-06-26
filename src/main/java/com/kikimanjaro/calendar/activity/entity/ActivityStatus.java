package com.kikimanjaro.calendar.activity.entity;

public enum ActivityStatus {
    PENDING("En définition"),
    IN_PROGRESS("En cours"),
    TEST("En test"),
    DONE("Terminé"),
    NOT_DEFINED("Non défini");

    /**
     * The String corresponding to the ActivityStatus
     */
    private final String stringValue;

    ActivityStatus(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * <p>
     * Create an ActivityStatus from a corresponding String,
     * if none of the ActivityStatus is found it will return NOT_DEFINED
     * </p>
     *
     * @param str The string needed to create an ActivityStatus
     * @return The ActivityStatus corresponding to the String
     */
    public static ActivityStatus fromString(String str) {
        if (str == null) {
            return NOT_DEFINED;
        } else if (str.equals(PENDING.stringValue)) {
            return PENDING;
        } else if (str.equals(IN_PROGRESS.stringValue)) {
            return IN_PROGRESS;
        } else if (str.equals(TEST.stringValue)) {
            return TEST;
        } else if (str.equals(DONE.stringValue)) {
            return DONE;
        } else {
            return NOT_DEFINED;
        }
    }

    /**
     * <p>
     * Get a String from a corresponding ActivityStatus
     * </p>
     *
     * @return The String corresponding to the ActivityStatus
     */
    @Override
    public String toString() {
        return this.stringValue;
    }
}
