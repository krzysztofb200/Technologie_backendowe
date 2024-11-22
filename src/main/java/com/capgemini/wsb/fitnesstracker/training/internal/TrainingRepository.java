package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Query searching trainings by user id. It matches by exact match.
     *
     * @param userId id of the user to search his trainings
     * @return {@link List} containing found trainings of user
     */
    default List<Training> findAllByUserId(Long userId){
        return findAll().stream().filter(training -> {
            assert training.getUser().getId() != null;
            return training.getUser().getId().equals(userId);
        }).toList();
    }
    /**
     * Query searching all trainings ended after date.
     *
     * @param time date after which trainings ended
     * @return {@link List} containing all found trainings
     */
    default List<Training> findAllAfterDate(Date time){
        return findAll().stream().filter(training -> training.getEndTime().after(time)).toList();
    }
    /**
     * Query searching all trainings by activity.
     *
     * @param activityType type of activity of the training
     * @return {@link List} containing all found trainings
     */
    default List<Training> findAllByActivity(ActivityType activityType){
        return findAll().stream().filter(training -> training.getActivityType().equals(activityType)).toList();
    }
    /**
     * updates {@link Training}.
     *
     * @param trainingId id of training to update
     * @param updatedTraining data to update the training
     * @return {@link Optional} containing updated training or {@link Optional#empty()} if none matched
     */
    default Optional<Training> updateTraining(Long trainingId, Training updatedTraining){
        return findAll().stream().filter(training -> Objects.equals(training.getId(), trainingId)).map(training -> {
            if(updatedTraining.getEndTime() != null){
                training.setEndTime(updatedTraining.getEndTime());
            }
            if(updatedTraining.getStartTime() != null){
                training.setStartTime(updatedTraining.getStartTime());
            }
            if(updatedTraining.getActivityType() != null){
                training.setActivityType(updatedTraining.getActivityType());
            }
            if(updatedTraining.getAverageSpeed() != training.getAverageSpeed()){
                training.setAverageSpeed(updatedTraining.getAverageSpeed());
            }
            if(updatedTraining.getDistance() != training.getDistance()){
                training.setDistance(updatedTraining.getDistance());
            }
            return save(training);
        }).findFirst();
    }
}
