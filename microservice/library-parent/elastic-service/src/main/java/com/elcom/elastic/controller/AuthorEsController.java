package com.elcom.elastic.controller;

import com.elcom.elastic.model.AuthorEs;
import com.elcom.elastic.model.BookEs;
import com.elcom.elastic.model.dto.AuthorizationResponseDTO;
import com.elcom.elastic.service.AuthorEsService;
import com.elcom.elastic.service.BookEsService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AuthorEsController extends BaseController{
    @Autowired
    private AuthorEsService authorEsService;

    @GetMapping("/elastic/author/findAll")
    public ResponseMessage findAllAuthor(@RequestHeader Map<String, String> headerParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Iterable<AuthorEs> allAuthorEs = authorEsService.findAll();
            if(allAuthorEs == null ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                allAuthorEs));
            }
        }
        return response;
    }

    @PostMapping("/elastic/author/findByName")
    public ResponseMessage findByName(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            AuthorEs authorEs = authorEsService.findByName((String)bodyParam.get("name"));
            if(authorEs == null ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                authorEs));
            }
        }
        return response;
    }

    @PostMapping("elastic/author/save")
    public ResponseMessage save(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response ;
        Integer id = (Integer)bodyParam.get("id");
        String name = (String)bodyParam.get("name");
        Long Id = Long.valueOf(id);
        AuthorEs authorEs = new AuthorEs();
        authorEs.setId(Id);
        authorEs.setName(name);
        authorEsService.save(authorEs);
        response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        authorEs));

        return response;
    }

    @PutMapping("elastic/author/update")
    public ResponseMessage update(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response =null;
        Integer id = (Integer)bodyParam.get("id");
        Long Id = Long.valueOf(id);
        String name = (String)bodyParam.get("name");
        AuthorEs authorEs = authorEsService.findById(Id);
        if(authorEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            authorEs.setName(name);
            authorEsService.save(authorEs);
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            authorEs));
        }
        return response;
    }

    @DeleteMapping("elastic/author/delete")
    public ResponseMessage delete(@PathVariable String id){
        ResponseMessage response = null;
        AuthorEs authorEs = authorEsService.findById(Long.parseLong(id));
        if(authorEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            authorEsService.delete(Long.parseLong(id));
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            null));
        }
        return response;
    }
}
