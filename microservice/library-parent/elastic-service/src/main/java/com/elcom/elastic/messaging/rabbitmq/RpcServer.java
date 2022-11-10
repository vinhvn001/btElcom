package com.elcom.elastic.messaging.rabbitmq;


import com.elcom.constant.ResourcePath;

import com.elcom.elastic.controller.AuthorEsController;
import com.elcom.elastic.controller.BookEsController;
import com.elcom.elastic.controller.CategoryEsController;
import com.elcom.elastic.exception.ValidationException;
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
    private BookEsController bookEsController;

    @Autowired
    private AuthorEsController authorEsController;

    @Autowired
    private CategoryEsController categoryEsController;



    @RabbitListener(queues = "${elastic.rpc.queue}")
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
                        if ("/elastic/book/findAll".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = bookEsController.findAllBook(headerParam);
                        }
                        else if ("/elastic/author/findAll".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.findAllAuthor(headerParam);
                        }else if ("/elastic/category/findAll".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = categoryEsController.findAllCategory(headerParam);
                        }
                        break;
                    case "POST":
                        if ("/elastic/book/findByName".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = bookEsController.findByName(headerParam,bodyParam);
                        }else if ("/elastic/book/save".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = bookEsController.save(bodyParam);
                        }
                        else if ("/elastic/author/save".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.save(bodyParam);
                        }
                        else if ("/elastic/category/save".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = categoryEsController.save(bodyParam);
                        }else if ("/elastic/author/findByName".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.findByName(headerParam,bodyParam);
                        }else if ("/elastic/category/findByName".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = categoryEsController.findByName(headerParam,bodyParam);
                        }
                        break;
                    case "PUT":
                        if ("/elastic/book/update".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = bookEsController.update(bodyParam);
                        }else if ("/elastic/author/update".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.update(bodyParam);
                        }
                        else if ("/elastic/category/update".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = categoryEsController.update(bodyParam);
                        }

                        break;
                    case "PATCH":
                        break;
                    case "DELETE":
                        if ("/elastic/book/delete".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = bookEsController.delete(pathParam);
                        }else if ("/elastic/author/delete".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.delete(pathParam);
                        }else if ("/elastic/category/delete".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorEsController.delete(pathParam);
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
