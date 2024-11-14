package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    TrainingDTO toDTO(Training training) {
        return new TrainingDTO(new UserDto(training.getUser().getId(),
                                            training.getUser().getFirstName(),
                                            training.getUser().getLastName(),
                                            training.getUser().getBirthdate(),
                                            training.getUser().getEmail()),
                                training.getStartTime(),
                                training.getEndTime(),
                                training.getActivityType(),
                                training.getDistance(),
                                training.getAverageSpeed());
    }

    Training toEntity(TrainingDTO trainingDTO) {
        return new Training(new User(trainingDTO.getUser().firstName(),
                                        trainingDTO.getUser().lastName(),
                                        trainingDTO.getUser().birthdate(),
                                        trainingDTO.getUser().email()),
                            trainingDTO.getStartTime(),
                            trainingDTO.getEndTime(),
                            trainingDTO.getActivityType(),
                            trainingDTO.getDistance(),
                            trainingDTO.getAverageSpeed());
    }

}
