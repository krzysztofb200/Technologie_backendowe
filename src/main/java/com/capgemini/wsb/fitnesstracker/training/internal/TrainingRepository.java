package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {
    /**TODO
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default List<Training> findAllByUserId(Long userId){
        return findAll().stream().filter(training -> {
            assert training.getUser().getId() != null;
            return training.getUser().getId().equals(userId);
        }).toList();
    }
    /**TODO
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default List<Training> findAllAfterDate(Date time){
        return findAll().stream().filter(training -> training.getEndTime().after(time)).toList();
    }
    /**TODO
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default List<Training> findAllByActivity(ActivityType activityType){
        return findAll().stream().filter(training -> training.getActivityType().equals(activityType)).toList();
    }
    /**TODO
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
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
