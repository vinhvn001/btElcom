package com.elcom.library.controller;


import com.elcom.library.model.Author;
import com.elcom.library.model.dto.AuthorizationResponseDTO;
import com.elcom.library.service.AuthorService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;


@RestController
public class AuthorController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    @GetMapping("/library/author/findAll")
    public ResponseMessage findAllAuthor(@RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<Author> allAuthor = authorService.findAll();
            if (allAuthor == null || allAuthor.isEmpty()) {
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        allAuthor));
            }
        }
        return response;
    }

    @GetMapping("/library/author/{id}")
    public ResponseMessage findAuthorById(@PathVariable String id, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Author author = authorService.findById(Long.parseLong(id));
            if (author == null ) {
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.NOT_FOUND.value(), "Author không tồn tại", null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                author));
            }
        }
        return response;
    }

    @PostMapping("/library/author")
    public ResponseMessage createAuthor(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                if (bodyParam == null || bodyParam.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid param value",
                            new MessageContent(HttpStatus.BAD_REQUEST.value(), "Invalid param value", null));
                }else{
                    String authorName = (String)bodyParam.get("authorName");

                    Author existAuthor = null;
                    if(authorName != null){
                        existAuthor = authorService.findByAuthorName(authorName);
                    }
                    if(existAuthor != null){
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Author đã tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Author đã tồn tại", null));
                    }else{
                        try {
                            authorService.create(authorName);
                            response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                                    new MessageContent(authorName));
                        } catch (Exception ex){
                            response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                        }
                    }
                }
            }else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo author mới",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo author mới", null));
            }
        }
        return response;
    }

    @PutMapping("/library/author/{id}")
    public ResponseMessage updateAuthor(@PathVariable String id, @RequestBody Map<String, Object> bodyParam, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                Long Id = Long.parseLong(id);
                Author currentAuthor = authorService.findById(Id);
                if(currentAuthor == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Invalid param value",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Invalid param value", null));
                }else{
                    String authorName = (String)bodyParam.get("authorName");
                    Author existAuthor = null;
                    if(authorName != null){
                        existAuthor = authorService.findByAuthorName(authorName);
                    }
                    if(existAuthor != null){
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Author đã tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Author đã tồn tại", null));
                    }else{
                        try {
                            authorService.update(Id, authorName);
                            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                                    new MessageContent(authorName));
                        } catch (Exception ex){
                            response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                        }
                    }
                }
            }else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin author",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin author", null));
            }
        }
        return response;
    }

    @DeleteMapping("/library/author/{id}")
    public ResponseMessage deleteAuthor(@PathVariable String id,  @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                Long Id = Long.parseLong(id);
                Author currentAuthor = authorService.findById(Id);
                if(currentAuthor == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Invalid param value",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Invalid param value", null));
                }else{
                    authorService.delete(Id);
                    response =  new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                            new MessageContent(null));
                }
            }else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin author",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin author", null));
            }
        }
        return response;
    }

}


