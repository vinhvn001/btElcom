/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.controller;

import com.elcom.constant.ResourcePath;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(ResourcePath.VERSION)
public class GatewayController extends BaseController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);
    
    //GET
    @RequestMapping(value = "**", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMethod(@RequestParam Map<String, String> reqParam,
            @RequestHeader Map<String, String> headers, HttpServletRequest req) throws JsonProcessingException {
        return processRequest("GET", reqParam, null, headers, req);
    }

    //POST
    @RequestMapping(value = "**", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postMethod(@RequestParam Map<String, String> reqParam,
            @RequestBody(required = false) Map<String, Object> requestBody, @RequestHeader Map<String, String> headers,
            HttpServletRequest req) throws JsonProcessingException {
        return processRequest("POST", reqParam, requestBody, headers, req);
    }

    //PUT
    @RequestMapping(value = "**", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putMethod(@RequestParam Map<String, String> reqParam,
            @RequestBody(required = false) Map<String, Object> requestBody, @RequestHeader Map<String, String> headers,
            HttpServletRequest req) throws JsonProcessingException {
        return processRequest("PUT", reqParam, requestBody, headers, req);
    }

    //PATCH
    @RequestMapping(value = "**", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patchMethod(@RequestParam Map<String, String> reqParam,
            @RequestBody(required = false) Map<String, Object> requestBody, @RequestHeader Map<String, String> headers,
            HttpServletRequest req) throws JsonProcessingException {
        return processRequest("PATCH", reqParam, requestBody, headers, req);
    }

    //DELETE
    @RequestMapping(value = "**", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteMethod(@RequestParam Map<String, String> reqParam,
            @RequestBody(required = false) Map<String, Object> requestBody, @RequestHeader Map<String, String> headers,
            HttpServletRequest req) throws JsonProcessingException {
        return processRequest("DELETE", reqParam, requestBody, headers, req);
    }
}
