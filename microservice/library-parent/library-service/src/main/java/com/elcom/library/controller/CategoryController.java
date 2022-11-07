package com.elcom.library.controller;


import com.elcom.library.model.Category;
import com.elcom.library.model.dto.AuthorizationResponseDTO;
import com.elcom.library.service.CategoryService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController extends BaseController{
    @Autowired
    private  CategoryService categoryService;

     @GetMapping("/library/category/findAll")
    public ResponseMessage findAllCategory(@RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            List<Category> allCategory = categoryService.findAll();
            if (allCategory == null || allCategory.isEmpty()) {
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        allCategory));
            }
        }
        return response;
    }

    @GetMapping("/library/category/{id}")
    public ResponseMessage findCategoryById(@PathVariable String id, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Category category = categoryService.findById(Long.parseLong(id));
            if (category == null ) {
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.NOT_FOUND.value(), "Author không tồn tại", null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                category));
            }
        }
        return response;
    }

    @PostMapping("/library/category")
    public ResponseMessage createCategory(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){
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
                    String categoryName = (String)bodyParam.get("categoryName");

                    Category existCategory = null;
                    if(categoryName != null){
                        existCategory = categoryService.findByCategoryName(categoryName);
                    }
                    if(existCategory != null){
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Category đã tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Category đã tồn tại", null));
                    }else{
                        try {
                            categoryService.create(categoryName);
                            response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                                    new MessageContent(categoryName));
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

    @PutMapping("/library/category/{id}")
    public ResponseMessage updateCategory(@PathVariable String id, @RequestBody Map<String, Object> bodyParam, @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                Long Id = Long.parseLong(id);
                Category currentCategory = categoryService.findById(Id);
                if(currentCategory == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Category không tồn tại",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Category không tồn tại ", null));
                }else{
                    String categoryName = (String)bodyParam.get("categoryName");
                    Category existCategory = null;
                    if(categoryName != null){
                        existCategory = categoryService.findByCategoryName(categoryName);
                    }
                    if(existCategory != null){
                        response = new ResponseMessage(HttpStatus.CONFLICT.value(), "Category đã tồn tại",
                                new MessageContent(HttpStatus.CONFLICT.value(), "Category đã tồn tại", null));
                    }else{
                        try {
                            categoryService.update(Id, categoryName);
                            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                                    new MessageContent(categoryName));
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

    @DeleteMapping("/library/category/{id}")
    public ResponseMessage deleteCategory(@PathVariable String id,  @RequestHeader Map<String, String> headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                Long Id = Long.parseLong(id);
                Category currentCategory = categoryService.findById(Id);
                if(currentCategory == null){
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Invalid param value",
                            new MessageContent(HttpStatus.NOT_FOUND.value(), "Invalid param value", null));
                }else{
                    categoryService.delete(Id);
                    response =  new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                            new MessageContent(null));
                }
            }else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin category",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền sửa thông tin category", null));
            }
        }
        return response;
    }
}
