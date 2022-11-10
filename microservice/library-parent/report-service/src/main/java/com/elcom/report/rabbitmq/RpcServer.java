package com.elcom.report.rabbitmq;


import com.elcom.constant.ResourcePath;
import com.elcom.message.RequestMessage;
import com.elcom.message.ResponseMessage;
import com.elcom.report.controller.ReportController;
import com.elcom.report.exception.ValidationException;
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
    ReportController reportController;




    @RabbitListener(queues = "${report.rpc.queue}")
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


                        break;
                    case "POST":
                        if ("/report/findBookByAuthor".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = reportController.statisticBookByAuthor(headerParam, bodyParam);
                        }else if ("/report/findBookByCategory".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = reportController.statisticBookByCategory(headerParam, bodyParam);
                        }else if ("/report/findBookByLetter".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = reportController.statisticBookByLetter(headerParam, bodyParam);
                        }
                        else if ("/report/bookInTime".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = reportController.maxBookInTime(headerParam, bodyParam);
                        }else if ("/report/findBorrowInTime".equalsIgnoreCase(requestPath) ) // Get details
                        {
                            response = reportController.borrowInTime(headerParam, bodyParam);
                        }


                        break;
                    case "PUT":

                        break;
                    case "PATCH":
                        break;
                    case "DELETE":

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
