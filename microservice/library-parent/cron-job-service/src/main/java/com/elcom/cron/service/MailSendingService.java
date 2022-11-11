package com.elcom.cron.service;


import com.elcom.cron.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MailSendingService extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendingService.class);

    @Autowired
    private JavaMailSender mailSender;


    // send mail every 7 a.m
    @Scheduled(cron = "0 0 7 * * *")
    public void sendReportMail(){
        String email = "thevinhvn001@gmail.com";
        LOGGER.info("Send report to email {}", email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinhvn010@gmail.com");
        message.setTo(email);
        message.setSubject("List expired borrow ");
        List<Object> result = expiredBorrow();
        message.setText("Amount is 0");
        if(result == null){
        }else
        message.setText("List expired borrow : "+ result);
        mailSender.send(message);
    }
//    public static void main(String[]args){
//
//        sendReportMail();
//    }
}
