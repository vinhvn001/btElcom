package com.elcom.cron.controller;

import com.elcom.cron.model.AuthorizationResponseDTO;
import com.elcom.cron.service.MailSendingService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MailController extends BaseController{

    @Autowired
    private MailSendingService mailSendingService;

    @GetMapping("/cron/sendMail")
    public ResponseMessage sendMail(@RequestHeader Map<String,String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                mailSendingService.sendReportMail();
                List<Object> result = expiredBorrow();
                    response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    result));

            }else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền nay",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền nay", null));
            }
        }

        return response;

    }

}
