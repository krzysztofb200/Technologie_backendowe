package com.capgemini.wsb.fitnesstracker.training.internal;

import jakarta.annotation.Nullable;

import java.util.Date;

public class TrainingUserDto {
    @Nullable
    private final Long trainingId;
    private final Long userId;
    private final Date startTime;
    private final Date endTime;
    private final ActivityType activityType;
    private final double distance;
    private final double averageSpeed;

    public TrainingUserDto(@Nullable Long trainingId, Long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public double getDistance() {
        return distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    @Nullable
    public Long getTrainingId() {
        return trainingId;
    }
}
