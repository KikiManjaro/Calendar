package com.kikimanjaro.calendar.activity.service;

import com.kikimanjaro.calendar.activity.entity.ActivityStatus;
import com.kikimanjaro.calendar.activity.entity.IActivity;

import java.awt.*;
import java.util.List;

public interface IActivityService {

    List<IActivity> findActivity(int day, int month, int year);

    void registerActivity(IActivity activity);

    void updateActivity(IActivity activity);

    void deleteActivity(IActivity activity);

    Color getColorFromStatus(ActivityStatus status);
}
