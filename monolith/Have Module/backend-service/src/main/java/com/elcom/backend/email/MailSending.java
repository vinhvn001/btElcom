package com.elcom.backend.email;


import com.elcom.data.service.BorrowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Service
public class MailSending {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSending.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BorrowService borrowService;

    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);

    Date start = Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    Date end = Date.from(today.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    //@Scheduled(cron = "0 5 0,10 ? * * *")


    @Scheduled(cron = "0 0 7 * * *")
    public void sendReportMail(){
        String email = "thevinhvn001@gmail.com";
        LOGGER.info("Send report to email {}", email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinhvn009@gmail.com");
        message.setTo(email);
        message.setSubject("Borrowed book amount yesterday ");
        Integer amount = borrowService.borrowAmountInTime(start, end);
        if(amount == null){
            message.setText("Amount is 0");
        }else
        message.setText("Amount is "+ amount);
        mailSender.send(message);
    }
}
