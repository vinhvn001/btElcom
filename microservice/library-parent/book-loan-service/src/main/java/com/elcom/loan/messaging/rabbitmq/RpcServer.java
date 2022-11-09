package com.elcom.loan.messaging.rabbitmq;


import com.elcom.constant.ResourcePath;
import com.elcom.loan.controller.BorrowController;
import com.elcom.loan.exception.ValidationException;
import com.elcom.message.RequestMessage;
import com.elcom.message.ResponseMessage;
import com.elcom.utils.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;


public class RpcServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);



    @Autowired
    BorrowController borrowController;


    @RabbitListener(queues = "${loan.rpc.queue}")
    public String processService(String json) throws ValidationException {
        try {
            LOGGER.info(" [-->] Server received request for " + json);
            ObjectMapper mapper = new ObjectMapper();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setDateFormat(df);
            RequestMessage request = mapper.readValue(json, RequestMessage.class);

            //Process here
            ResponseMessage response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
            if (request != null) {
                String requestPath = request.getRequestPath().replace(request.getVersion() != null
                        ? request.getVersion() : ResourcePath.VERSION, "");
                String urlParam = request.getUrlParam();
                String pathParam = request.getPathParam();
                Map<String, Object> bodyParam = request.getBodyParam();
                Map<String, String> headerParam = request.getHeaderParam();
                //GatewayDebugUtil.debug(requestPath, urlParam, pathParam, bodyParam, headerParam);
                LOGGER.info(" [-->] Server received requestPath =========>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + requestPath);

                switch (request.getRequestMethod()) {
                    case "GET":
                        if ("/loan/findAll".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = borrowController.findAll(headerParam);
                        }

                        break;
                    case "POST":
                        if ("/loan/create".equalsIgnoreCase(requestPath)) // Insert
                        {
                            response = borrowController.create(headerParam, bodyParam );
                        }else if("/loan/return".equalsIgnoreCase(requestPath))
                        {
                            response = borrowController.returnBorrow( headerParam,pathParam);
                        }else if("/loan/findByBookName".equalsIgnoreCase(requestPath))
                        {
                            response = borrowController.findByBookName( headerParam,bodyParam);
                        }else if("/loan/findByUsername".equalsIgnoreCase(requestPath))
                        {
                            response = borrowController.findByUsername( headerParam,bodyParam);
                        }

                        break;
                    case "PUT":
                        if ("/loan/update".equalsIgnoreCase(requestPath)) // Insert
                        {
                            response = borrowController.update(pathParam,headerParam, bodyParam );
                        }
                        break;
                    case "PATCH":
                        break;
                    case "DELETE":
                        if ("/loan/delete".equalsIgnoreCase(requestPath)) // Insert
                        {
                            response = borrowController.delete(pathParam,headerParam );
                        }
                        break;
                    default:
                        break;
                }
            }
            LOGGER.info(" [<--] Server returned " + response != null ? response.toJsonString() : "null");
            return response != null ? response.toJsonString() : null;
        } catch (Exception ex) {
            LOGGER.error("Error to processService >>> " + StringUtil.printException(ex));
            ex.printStackTrace();
        }
        return null;
    }
}
