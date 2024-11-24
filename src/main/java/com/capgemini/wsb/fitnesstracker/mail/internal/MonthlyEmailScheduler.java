package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonthlyEmailScheduler {
    @Autowired
    private final EmailSenderImpl emailSender;
    @Autowired
    private final TrainingProvider trainingProvider;
    @Autowired
    private final UserProvider userProvider;

    @Scheduled(cron = "0 0 23 L * ?")
    public void sendMonthlyReports() {
        for(User user: userProvider.findAllUsers()){
            emailSender.send(new EmailDto(user.getEmail(), "Monthly trainings report", trainingProvider.getTrainingById(user.getId()).toString()));
        }
    }
}