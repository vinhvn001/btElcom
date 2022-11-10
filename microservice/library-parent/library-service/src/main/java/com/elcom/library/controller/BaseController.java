package com.elcom.library.controller;

import com.elcom.constant.ResourcePath;
import com.elcom.library.messaging.rabbitmq.RabbitMQClient;
import com.elcom.library.messaging.rabbitmq.RabbitMQProperties;
import com.elcom.library.model.dto.AuthorizationResponseDTO;
import com.elcom.library.model.dto.BookDTO;
import com.elcom.message.MessageContent;
import com.elcom.message.RequestMessage;
import com.elcom.message.ResponseMessage;
import com.elcom.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

    public void saveBookToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("POST");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_SAVE_BOOK_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Save book to elastic - result: " + result);

    }
    public void updateBookToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("PUT");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_UPDATE_BOOK_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Update book to elastic - result: " + result);

    }
    public void deleteBookFromElastic(Long id){
        //call Elastic Rpc
        String Id = String.valueOf(id);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("DELETE");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_DELETE_BOOK_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(null);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        elasticRpcRequest.setPathParam(Id);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Delete book from elastic - result: " + result);

    }

    public void saveAuthorToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("POST");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_SAVE_AUTHOR_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Save author to elastic - result: " + result);
    }
    public void updateAuthorToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("PUT");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_UPDATE_AUTHOR_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Update author to elastic - result: " + result);
    }
    public void deleteAuthorFromElastic(Long id){
        //call Elastic Rpc
        String Id = String.valueOf(id);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("DELETE");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_DELETE_AUTHOR_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(null);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        elasticRpcRequest.setPathParam(Id);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Delete author from elastic - result: " + result);
    }

    public void saveCategoryToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("POST");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_SAVE_CATEGORY_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Save category to elastic - result: " + result);
    }
    public void updateCategoryToElastic(Long id, String name){
        //call Elastic Rpc
        Map<String,Object> bodyParam = new HashMap<>();
        bodyParam.put("id", id);
        bodyParam.put("name", name);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("PUT");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_UPDATE_CATEGORY_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(bodyParam);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Update category to elastic - result: " + result);
    }
    public void deleteCategoryFromElastic(Long id){
        //call Elastic Rpc
        String Id = String.valueOf(id);
        RequestMessage elasticRpcRequest = new RequestMessage();
        elasticRpcRequest.setRequestMethod("DELETE");
        elasticRpcRequest.setRequestPath(RabbitMQProperties.ELASTIC_RPC_DELETE_CATEGORY_URL);
        elasticRpcRequest.setVersion(ResourcePath.VERSION);
        elasticRpcRequest.setBodyParam(null);
        elasticRpcRequest.setUrlParam(null);
        elasticRpcRequest.setHeaderParam(null);
        elasticRpcRequest.setPathParam(Id);
        String result = rabbitMQClient.callRpcService(RabbitMQProperties.ELASTIC_RPC_EXCHANGE,
                RabbitMQProperties.ELASTIC_RPC_QUEUE, RabbitMQProperties.ELASTIC_RPC_KEY, elasticRpcRequest.toJsonString());
        LOGGER.info("Delete category from elastic - result: " + result);
    }
}
