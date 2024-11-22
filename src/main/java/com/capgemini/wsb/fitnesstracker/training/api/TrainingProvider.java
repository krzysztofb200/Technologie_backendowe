package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves all trainings.
     *
     * @return A {@link List} containing the all found trainings,
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all trainings for user.
     *
     * @param userId id of the user to search his trainings
     * @return {@link List} containing found trainings of user
     */
    List<Training> getTrainingById(Long userId);

    /**
     * Retrieves all trainings ended after date.
     *
     * @param time date after which trainings ended
     * @return {@link List} containing all found trainings
     */
    List<Training> getAllTrainingsAfterDate(Date time);

    /**
     * Retrieves all trainings by activity.
     *
     * @param activityType type of activity of the training
     * @return {@link List} containing all found trainings
     */
    List<Training> getAllTrainingsByActivity(ActivityType activityType);

    /**
     * Creates {@link Training}
     *
     * @return An {@link Training} that has been created
     */
    Training createTraining(Training training);

    /**
     * updates {@link Training}.
     *
     * @return An {@link Optional} {@link Training} that has been updated, or {@link Optional#empty()} if not found
     */
    Optional<Training> updateTraining(Long id, Training training);
}
