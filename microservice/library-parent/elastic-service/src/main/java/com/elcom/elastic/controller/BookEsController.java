package com.elcom.elastic.controller;

import com.elcom.elastic.model.BookEs;
import com.elcom.elastic.model.dto.AuthorizationResponseDTO;
import com.elcom.elastic.service.BookEsService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookEsController extends BaseController{

    @Autowired
    private BookEsService bookEsService;

    @GetMapping("/elastic/book/findAll")
    public ResponseMessage findAllBook(@RequestHeader Map<String, String> headerParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Iterable<BookEs> allBookEs = bookEsService.findAll();
            if(allBookEs == null ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                allBookEs));
            }
        }
        return response;
    }

    @PostMapping("/elastic/book/findByName")
    public ResponseMessage findByName(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<BookEs> bookEs = bookEsService.findByName((String)bodyParam.get("name"));
            if(bookEs == null || bookEs.isEmpty() ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                bookEs));
            }
        }
        return response;
    }

    @PostMapping("elastic/book/save")
    public ResponseMessage save(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response ;
        Integer id = (Integer)bodyParam.get("id");
        String name = (String)bodyParam.get("name");
        Long Id = Long.valueOf(id);
        BookEs bookEs = new BookEs();
        bookEs.setId(Id);
        bookEs.setName(name);
        bookEsService.save(bookEs);
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            bookEs));

        return response;
    }

    @PutMapping("elastic/book/update")
    public ResponseMessage update(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response =null;
        Integer id = (Integer)bodyParam.get("id");
        String name = (String)bodyParam.get("name");
        Long Id = Long.valueOf(id);
        BookEs bookEs = bookEsService.findById(Id);
        if(bookEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            bookEs.setName(name);
            bookEsService.save(bookEs);
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            bookEs));
        }
        return response;
    }

    @DeleteMapping("elastic/book/delete")
    public ResponseMessage delete(@PathVariable String id){
        ResponseMessage response = null;
        BookEs bookEs = bookEsService.findById(Long.parseLong(id));
        if(bookEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            bookEsService.delete(Long.parseLong(id));
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            null));
        }
        return response;
    }
}
