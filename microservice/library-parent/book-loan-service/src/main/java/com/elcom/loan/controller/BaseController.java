package com.elcom.loan.controller;

import com.elcom.constant.ResourcePath;

import com.elcom.loan.model.dto.BorrowBookResponseDTO;
import com.elcom.message.MessageContent;
import com.elcom.message.RequestMessage;
import com.elcom.message.ResponseMessage;
import com.elcom.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.elcom.loan.messaging.rabbitmq.RabbitMQClient;
import com.elcom.loan.messaging.rabbitmq.RabbitMQProperties;
import com.elcom.loan.model.dto.AuthorizationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
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

    public BorrowBookResponseDTO borrowBook(Map<String, String> headerMap, Map<String, Object> bodyParam) {
        //Authen -> call rpc authen headerMap
        RequestMessage libraryRpcRequest = new RequestMessage();
        libraryRpcRequest.setRequestMethod("POST");
        libraryRpcRequest.setRequestPath(RabbitMQProperties.LIBRARY_RPC_BORROW_URL);
        libraryRpcRequest.setVersion(ResourcePath.VERSION);
        libraryRpcRequest.setBodyParam(bodyParam);
        libraryRpcRequest.setUrlParam(null);
        libraryRpcRequest.setHeaderParam(headerMap);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LIBRARY_RPC_EXCHANGE,
                RabbitMQProperties.LIBRARY_RPC_QUEUE, RabbitMQProperties.LIBRARY_RPC_KEY, libraryRpcRequest.toJsonString());
        LOGGER.info("borrow a book - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
         //   DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
          //  mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi library service : " + ex.toString());
                return null;
            }

            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    if (data != null) {
                        BorrowBookResponseDTO dto = null;
                        if (data.getClass() == LinkedHashMap.class) {
                            dto = new BorrowBookResponseDTO((Map<String, Object>) data);
                        } else if (data.getClass() == BorrowBookResponseDTO.class) {
                            dto = (BorrowBookResponseDTO) data;
                        }
                        if (dto != null && !StringUtil.isNullOrEmpty(dto.getBookName())) {
                            return dto;
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã BorrowBookResponseDTO khi gọi library service : " + ex.toString());
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

    public BorrowBookResponseDTO returnBook(Map<String, String> headerMap, Map<String, Object> bodyParam) {
        //Authen -> call rpc authen headerMap
        RequestMessage libraryRpcRequest = new RequestMessage();
        libraryRpcRequest.setRequestMethod("POST");
        libraryRpcRequest.setRequestPath(RabbitMQProperties.LIBRARY_RPC_RETURN_URL);
        libraryRpcRequest.setVersion(ResourcePath.VERSION);
        libraryRpcRequest.setBodyParam(bodyParam);
        libraryRpcRequest.setUrlParam(null);
        libraryRpcRequest.setHeaderParam(headerMap);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.LIBRARY_RPC_EXCHANGE,
                RabbitMQProperties.LIBRARY_RPC_QUEUE, RabbitMQProperties.LIBRARY_RPC_KEY, libraryRpcRequest.toJsonString());
        LOGGER.info("return a book - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //   DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
            //  mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi library service : " + ex.toString());
                return null;
            }

            if (response != null && response.getStatus() == HttpStatus.OK.value()) {
                try {
                    //Process
                    MessageContent content = response.getData();
                    Object data = content.getData();
                    if (data != null) {
                        BorrowBookResponseDTO dto = null;
                        if (data.getClass() == LinkedHashMap.class) {
                            dto = new BorrowBookResponseDTO((Map<String, Object>) data);
                        } else if (data.getClass() == BorrowBookResponseDTO.class) {
                            dto = (BorrowBookResponseDTO) data;
                        }
                        if (dto != null && !StringUtil.isNullOrEmpty(dto.getBookName())) {
                            return dto;
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã BorrowBookResponseDTO khi gọi library service : " + ex.toString());
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

    public AuthorizationResponseDTO checkExistUser( Map<String, Object> bodyParam) {
        //Authen -> call rpc authen headerMap
        RequestMessage userRpcRequest = new RequestMessage();
        userRpcRequest.setRequestMethod("POST");
        userRpcRequest.setRequestPath(RabbitMQProperties.USER_RPC_EXIST_URL);
        userRpcRequest.setVersion(ResourcePath.VERSION);
        userRpcRequest.setBodyParam(bodyParam);
        userRpcRequest.setUrlParam(null);
        userRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.USER_RPC_EXCHANGE,
                RabbitMQProperties.USER_RPC_QUEUE, RabbitMQProperties.USER_RPC_KEY, userRpcRequest.toJsonString());
        LOGGER.info("check exist user - result: " + result);
        if (result != null) {
            ObjectMapper mapper = new ObjectMapper();
            //   DateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
            //  mapper.setDateFormat(df);
            ResponseMessage response = null;
            try {
                response = mapper.readValue(result, ResponseMessage.class);
            } catch (JsonProcessingException ex) {
                LOGGER.info("Lỗi parse json khi gọi user service : " + ex.toString());
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
                            dto = (AuthorizationResponseDTO)  data;
                        }
                        if (dto != null && !StringUtil.isNullOrEmpty(dto.getUsername())) {
                            return dto;
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.info("Lỗi giải mã AuthorizationResponseDTO khi gọi user service : " + ex.toString());
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
}
