package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    private final UserProvider userProvider;

    @Autowired
    public TrainingMapper(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    TrainingDto toDTO(Training training) {
        return new TrainingDto(training.getId(),
                new UserDto(training.getUser().getId(),
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

    Training toEntity(TrainingUserDto trainingUserDto) {
        User user = null;
        if (userProvider.getUser(trainingUserDto.getUserId()).isPresent()) {
            user = userProvider.getUser(trainingUserDto.getUserId()).get();
        }
        return new Training(
                user,
                trainingUserDto.getStartTime(),
                trainingUserDto.getEndTime(),
                trainingUserDto.getActivityType(),
                trainingUserDto.getDistance(),
                trainingUserDto.getAverageSpeed());
    }
}
