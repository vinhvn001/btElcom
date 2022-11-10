package com.elcom.report.controller;

import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import com.elcom.report.model.AuthorizationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController extends BaseController{

    @PostMapping("/report/findBookByAuthor")
    public ResponseMessage statisticBookByAuthor(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
             List<Object> result = findBookByAuthor(headerParam,bodyParam);
            if (result == null || result.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                result));
            }
        }
        return response;
    }

    @PostMapping("/report/findBookByCategory")
    public ResponseMessage statisticBookByCategory(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<Object> result = findBookByCategory(headerParam,bodyParam);
            if (result == null || result.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                result));
            }
        }
        return response;
    }

    @PostMapping("/report/findBookByLetter")
    public ResponseMessage statisticBookByLetter(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<Object> result = findBookByLetter(headerParam,bodyParam);
            if (result == null || result.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                result));
            }
        }
        return response;
    }

    @PostMapping("/report/bookInTime")
    public ResponseMessage maxBookInTime(@RequestHeader Map<String,String> headerParam,@RequestBody Map<String,Object> bodyParam) throws Exception {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{

            List<Object> result = maxBookInTime(bodyParam);
            if (result == null || result.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                result));
            }
        }
        return response;
    }

    @PostMapping("/report/borrowInTime")
    public ResponseMessage borrowInTime(@RequestHeader Map<String,String> headerParam,@RequestBody Map<String,Object> bodyParam) throws ParseException {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{

            List<Object> result = borrowInTime(bodyParam);
            if (result == null || result.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                result));
            }
        }
        return response;
    }
}
