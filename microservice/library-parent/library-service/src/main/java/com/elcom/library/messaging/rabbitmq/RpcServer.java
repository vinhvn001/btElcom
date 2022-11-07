package com.elcom.library.messaging.rabbitmq;


import com.elcom.constant.ResourcePath;
import com.elcom.library.controller.AuthorController;
import com.elcom.library.controller.BookController;
import com.elcom.library.controller.CategoryController;
import com.elcom.library.exception.ValidationException;
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
    private BookController bookController;

    @Autowired
    private AuthorController authorController;

    @Autowired
    private CategoryController categoryController;



    @RabbitListener(queues = "${library.rpc.queue}")
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
                        if ("/library/author/findAll".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = authorController.findAllAuthor(headerParam);
                        }else if("/library/author".equalsIgnoreCase(requestPath))
                        {
                            response = authorController.findAuthorById(pathParam, headerParam);
                        }else if("/library/category/findAll".equalsIgnoreCase(requestPath))
                        {
                            response = categoryController.findAllCategory(headerParam);
                        }else if("/library/category".equalsIgnoreCase(requestPath))
                        {
                            response = categoryController.findCategoryById(pathParam, headerParam);
                        }else if("/library/book/findAll".equalsIgnoreCase(requestPath))
                        {
                            response = bookController.findAllBook(headerParam);
                        }else if("/library/book".equalsIgnoreCase(requestPath))
                        {
                            response = bookController.findBookById(pathParam, headerParam);
                        }

                        break;
                    case "POST":
                        if ("/library/author".equalsIgnoreCase(requestPath)) // Insert
                        {
                            response = authorController.createAuthor(headerParam, bodyParam );
                        }else if("/library/category".equalsIgnoreCase(requestPath))
                        {
                            response = categoryController.createCategory(headerParam, bodyParam);
                        }else if("/library/book".equalsIgnoreCase(requestPath))
                        {
                            response = bookController.createBook(headerParam,bodyParam);
                        }

                        break;
                    case "PUT":
                        if ("/library/author".equalsIgnoreCase(requestPath) && !StringUtil.isNullOrEmpty(pathParam))//Update via service
                        {
                            response = authorController.updateAuthor(pathParam, bodyParam, headerParam);
                        }else if("/library/category".equalsIgnoreCase(requestPath))
                        {
                            response = categoryController.updateCategory(pathParam, bodyParam, headerParam);
                        }else if("/library/book".equalsIgnoreCase(requestPath))
                        {
                            response = bookController.updateBook(bodyParam, headerParam);
                        }
                        break;
                    case "PATCH":
                        break;
                    case "DELETE":
                        if ("/library/author".equalsIgnoreCase(requestPath) && pathParam != null && pathParam.length() > 0) // Delete by id
                        {
                            response = authorController.deleteAuthor(pathParam,headerParam);
                        }else if("/library/category".equalsIgnoreCase(requestPath))
                        {
                            response = categoryController.deleteCategory(pathParam, headerParam);
                        }else if("/library/book".equalsIgnoreCase(requestPath))
                        {
                            response = bookController.deleteBook(pathParam, headerParam);
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
