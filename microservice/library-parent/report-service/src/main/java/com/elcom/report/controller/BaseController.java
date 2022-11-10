package com.elcom.report.controller;

import com.elcom.constant.ResourcePath;

import com.elcom.message.MessageContent;
import com.elcom.message.RequestMessage;
import com.elcom.message.ResponseMessage;
import com.elcom.report.model.AuthorizationResponseDTO;
import com.elcom.report.rabbitmq.RabbitMQClient;
import com.elcom.report.rabbitmq.RabbitMQProperties;
import com.elcom.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RabbitMQClient rabbitMQClient;



   //check token id qua id service
    public AuthorizationResponseDTO authenticateToken(Map<String, String> headerMap) {
        //Authen -> call rpc authen headerMap
        RequestMessage userRpcRequest = new RequestMessage();
        userRpcRequest.setRequestMethod("POST");
        userRpcRequest.setRequestPath(RabbitMQProperties.USER_RPC_AUTHEN_URL);
        userRpcRequest.setVersion(ResourcePath.VERSION);
        userRpcRequest.setBodyParam(null);
        userRpcRequest.setUrlParam(null);
        userRpcRequest.setHeaderParam(headerMap);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.USER_RPC_EXCHANGE,
                RabbitMQProperties.USER_RPC_QUEUE, RabbitMQProperties.USER_RPC_KEY, userRpcRequest.toJsonString());
        LOGGER.info("authenToken - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi user service verify: " + ex.toString());
                return null;
            }

            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    if (data != null) {
                        AuthorizationResponseDTO dto = null;
                        if (data.getClass() == LinkedHashMap.class) {
                            dto = new AuthorizationResponseDTO((Map<String, Object>) data);
                        } else if (data.getClass() == AuthorizationResponseDTO.class) {
                            dto = (AuthorizationResponseDTO) data;
                        }
                        if (dto != null && !StringUtil.isNullOrEmpty(dto.getUsername())) {
                            return dto;
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã AuthorizationResponseDTO khi gọi user service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }
        return null;
    }

    public List<Object> findBookByAuthor(Map<String, String> headerParam, Map<String, Object> bodyParam){
        RequestMessage libraryRpcRequest = new RequestMessage();
        libraryRpcRequest.setRequestMethod("POST");
        libraryRpcRequest.setRequestPath(RabbitMQProperties.LIBRARY_RPC_BOOK_BY_AUTHOR_URL);
        libraryRpcRequest.setVersion(ResourcePath.VERSION);
        libraryRpcRequest.setBodyParam(bodyParam);
        libraryRpcRequest.setUrlParam(null);
        libraryRpcRequest.setHeaderParam(headerParam);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LIBRARY_RPC_EXCHANGE,
                RabbitMQProperties.LIBRARY_RPC_QUEUE, RabbitMQProperties.LIBRARY_RPC_KEY, libraryRpcRequest.toJsonString());
        LOGGER.info("Find book by author - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi library verify: " + ex.toString());
                return null;
            }
            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    List<Object> finalResult = (List<Object>) data;
                    return finalResult;
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã List khi gọi library service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }

    }

    public List<Object> findBookByCategory(Map<String, String> headerParam, Map<String, Object> bodyParam){
        RequestMessage libraryRpcRequest = new RequestMessage();
        libraryRpcRequest.setRequestMethod("POST");
        libraryRpcRequest.setRequestPath(RabbitMQProperties.LIBRARY_RPC_BOOK_BY_CATEGORY_URL);
        libraryRpcRequest.setVersion(ResourcePath.VERSION);
        libraryRpcRequest.setBodyParam(bodyParam);
        libraryRpcRequest.setUrlParam(null);
        libraryRpcRequest.setHeaderParam(headerParam);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LIBRARY_RPC_EXCHANGE,
                RabbitMQProperties.LIBRARY_RPC_QUEUE, RabbitMQProperties.LIBRARY_RPC_KEY, libraryRpcRequest.toJsonString());
        LOGGER.info("Find book by category - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi library verify: " + ex.toString());
                return null;
            }
            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    List<Object> finalResult = (List<Object>) data;
                    return finalResult;
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã List khi gọi library service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }

    }

    public List<Object> findBookByLetter(Map<String, String> headerParam, Map<String, Object> bodyParam){
        RequestMessage libraryRpcRequest = new RequestMessage();
        libraryRpcRequest.setRequestMethod("POST");
        libraryRpcRequest.setRequestPath(RabbitMQProperties.LIBRARY_RPC_BOOK_BY_LETTER_URL);
        libraryRpcRequest.setVersion(ResourcePath.VERSION);
        libraryRpcRequest.setBodyParam(bodyParam);
        libraryRpcRequest.setUrlParam(null);
        libraryRpcRequest.setHeaderParam(headerParam);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LIBRARY_RPC_EXCHANGE,
                RabbitMQProperties.LIBRARY_RPC_QUEUE, RabbitMQProperties.LIBRARY_RPC_KEY, libraryRpcRequest.toJsonString());
        LOGGER.info("Find book by first letter - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi library verify: " + ex.toString());
                return null;
            }
            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    List<Object> finalResult = (List<Object>) data;
                    return finalResult;
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã List khi gọi library service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }

    }

    public List<Object> maxBookInTime( Map<String, Object> bodyParam){
        RequestMessage loanRpcRequest = new RequestMessage();
        loanRpcRequest.setRequestMethod("POST");
        loanRpcRequest.setRequestPath(RabbitMQProperties.LOAN_RPC_BOOK_IN_TIME_URL);
        loanRpcRequest.setVersion(ResourcePath.VERSION);
        loanRpcRequest.setBodyParam(bodyParam);
        loanRpcRequest.setUrlParam(null);
        loanRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LOAN_RPC_EXCHANGE,
                RabbitMQProperties.LOAN_RPC_QUEUE, RabbitMQProperties.LOAN_RPC_KEY, loanRpcRequest.toJsonString());
        LOGGER.info("Find the most borrowed book in time - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi book-loan verify: " + ex.toString());
                return null;
            }
            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    List<Object> finalResult = (List<Object>) data;
                    return finalResult;
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã List khi gọi book-loan service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }

    }

    public List<Object> borrowInTime( Map<String, Object> bodyParam){
        RequestMessage loanRpcRequest = new RequestMessage();
        loanRpcRequest.setRequestMethod("POST");
        loanRpcRequest.setRequestPath(RabbitMQProperties.LOAN_RPC_BORROW_IN_TIME_URL);
        loanRpcRequest.setVersion(ResourcePath.VERSION);
        loanRpcRequest.setBodyParam(bodyParam);
        loanRpcRequest.setUrlParam(null);
        loanRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LOAN_RPC_EXCHANGE,
                RabbitMQProperties.LOAN_RPC_QUEUE, RabbitMQProperties.LOAN_RPC_KEY, loanRpcRequest.toJsonString());
        LOGGER.info("Find the borrow book in time - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi book-loan verify: " + ex.toString());
                return null;
            }
            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    List<Object> finalResult = (List<Object>) data;
                    return finalResult;
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã List khi gọi book-loan service verify: " + ex.toString());
                    return null;
                }
            } else {
                //Forbidden
                return null;
            }
        } else {
            //Forbidden
            return null;
        }

    }


}
