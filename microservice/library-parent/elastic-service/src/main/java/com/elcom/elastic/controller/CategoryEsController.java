package com.elcom.elastic.controller;

import com.elcom.elastic.model.CategoryEs;
import com.elcom.elastic.model.CategoryEs;
import com.elcom.elastic.model.dto.AuthorizationResponseDTO;

import com.elcom.elastic.service.CategoryEsService;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CategoryEsController extends BaseController{

    @Autowired
    private CategoryEsService categoryEsService;

    @GetMapping("/elastic/category/findAll")
    public ResponseMessage findAllCategory(@RequestHeader Map<String, String> headerParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            Iterable<CategoryEs> allCategoryEs = categoryEsService.findAll();
            if(allCategoryEs == null ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                allCategoryEs));
            }
        }
        return response;
    }

    @PostMapping("/elastic/category/findByName")
    public ResponseMessage findByName(@RequestHeader Map<String, String> headerParam, @RequestBody Map<String, Object> bodyParam){

        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            CategoryEs categoryEs = categoryEsService.findByName((String)bodyParam.get("name"));
            if(categoryEs == null ){
                response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                        new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
            }else{
                response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                categoryEs));
            }
        }
        return response;
    }

    @PostMapping("elastic/category/save")
    public ResponseMessage save(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response ;
        Integer id = (Integer)bodyParam.get("id");
        String name = (String)bodyParam.get("name");
        Long Id = Long.valueOf(id);
        CategoryEs categoryEs = new CategoryEs();
        categoryEs.setId(Id);
        categoryEs.setName(name);
        categoryEsService.save(categoryEs);
        response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                        categoryEs));

        return response;
    }

    @PutMapping("elastic/category/update")
    public ResponseMessage update(@RequestBody Map<String,Object> bodyParam){
        ResponseMessage response =null;
        Integer id = (Integer)bodyParam.get("id");
        String name = (String)bodyParam.get("name");
        Long Id = Long.valueOf(id);
        CategoryEs categoryEs = categoryEsService.findById(Id);
        if(categoryEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            categoryEs.setName(name);
            categoryEsService.save(categoryEs);
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            categoryEs));
        }
        return response;
    }

    @DeleteMapping("elastic/category/delete")
    public ResponseMessage delete(@PathVariable String id){
        ResponseMessage response = null;
        CategoryEs categoryEs = categoryEsService.findById(Long.parseLong(id));
        if(categoryEs ==null){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), null));
        }else {
            categoryEsService.delete(Long.parseLong(id));
            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                    new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            null));
        }
        return response;
    }
}
