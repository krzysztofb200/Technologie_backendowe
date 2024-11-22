package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonthlyEmailScheduler {

    private final EmailSenderImpl emailSender;
    private final UserProvider userProvider;

    @Scheduled(cron = "* * * * * ?")
    public void sendMonthlyReports() {
        emailSender.send(new EmailDto("mail@mail.com", "Monthly trainings report", "trainings"));
    }
}