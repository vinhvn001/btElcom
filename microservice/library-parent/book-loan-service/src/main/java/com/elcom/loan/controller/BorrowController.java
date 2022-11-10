package com.elcom.loan.controller;



import com.elcom.loan.model.dto.BorrowBookResponseDTO;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;


import com.elcom.loan.model.Borrow;
import com.elcom.loan.model.dto.AuthorizationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.elcom.loan.service.BorrowService;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BorrowController extends BaseController{

    @Autowired
    BorrowService borrowService;


    @GetMapping("/loan/findAll")
    public ResponseMessage findAll (@RequestHeader Map<String, String > headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                List<Borrow> allBorrow = borrowService.findAll();
                if (allBorrow == null || allBorrow.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                            new MessageContent(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), null));
                }else{
                    response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    allBorrow));
                }
            }else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn", null));
            }
        }

        return response;
    }
    @PostMapping("/loan/findByBookName")
    public ResponseMessage findByBookName(@RequestHeader Map<String, String > headerParam, @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                String bookName =(String)bodyParam.get("bookName");
                List<Borrow> borrows = borrowService.findByBookName(bookName);
                if (borrows == null || borrows.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                            new MessageContent(HttpStatus.NO_CONTENT.value(), "Không tồn tại bản ghi", null));
                }else{
                    response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    borrows));
                }
            }else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn", null));
            }
        }
        return response;
    }
    @PostMapping("/loan/findByUsername")
    public ResponseMessage findByUsername(@RequestHeader Map<String, String > headerParam, @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            String username =(String)bodyParam.get("username");
            if(dto.getRoleName().equalsIgnoreCase("admin")){
                List<Borrow> borrows = borrowService.findByUsername(username);
                if (borrows == null || borrows.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                            new MessageContent(HttpStatus.NO_CONTENT.value(), "Không tồn tại bản ghi", null));
                }else{
                    response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    borrows));
                }
            }else if(dto.getUsername().equalsIgnoreCase(username)){
                    List<Borrow> borrows = borrowService.findByUsername(username);
                if (borrows == null || borrows.isEmpty()) {
                    response = new ResponseMessage(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(),
                            new MessageContent(HttpStatus.NO_CONTENT.value(), "Không tồn tại bản ghi", null));
                }else{
                    response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                            new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                    borrows));
                }
            } else{
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn của user khác",
                        new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xem danh sách mượn của user khác", null));
            }
        }
        return response;
    }

    @PostMapping("/loan/create")
    public ResponseMessage create (@RequestHeader Map<String, String > headerParam, @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")) {
                BorrowBookResponseDTO borrowDto = borrowBook(headerParam, bodyParam);
                AuthorizationResponseDTO userDto = checkExistUser(bodyParam);
                if(borrowDto == null){
                    response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin sách bạn nhập không tồn tại", null));
                }else if(userDto == null){
                    response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin user nhập không tồn tại", null));
                }else
                 {
                    Borrow borrow = new Borrow();
                    borrow.setBookName(borrowDto.getBookName());
                    borrow.setUsername(borrowDto.getUsername());
                    borrow.setLimitDate(borrowDto.getLimitDate());
                    borrow.setBorrowedDate(new Timestamp(System.currentTimeMillis()));
                    try {
                        borrowService.save(borrow);
                        response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                                new MessageContent(borrow));
                    } catch (Exception ex) {
                        response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                    }
                }
            }else {
                String username = (String) bodyParam.get("username");
                BorrowBookResponseDTO borrowDto = borrowBook(headerParam, bodyParam);
                if (borrowDto == null) {
                    response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin sách bạn nhập không đúng", null));
                } else if (dto.getUsername().equalsIgnoreCase(username) ) {
                    Borrow borrow = new Borrow();
                    borrow.setBookName(borrowDto.getBookName());
                    borrow.setUsername(borrowDto.getUsername());
                    borrow.setLimitDate(borrowDto.getLimitDate());
                    borrow.setBorrowedDate(new Timestamp(System.currentTimeMillis()));
                    try {
                        borrowService.save(borrow);
                        response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                                new MessageContent(borrow));
                    } catch (Exception ex) {
                        response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                    }
                } else {
                    response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo bản ghi mượn sách cho user khác", null));
                }
            }
        }
        return response;
    }

    @PutMapping("/loan/update/{id}")
    public ResponseMessage update (@PathVariable String id, @RequestHeader Map<String, String > headerParam,
                                   @RequestBody Map<String, Object> bodyParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else {
            if (dto.getRoleName().equalsIgnoreCase("admin")) {
                Borrow currentBorrow = borrowService.findById(Long.parseLong(id));
                if(currentBorrow == null){
                    response = new ResponseMessage(new MessageContent(HttpStatus.NOT_FOUND.value(), "Bản ghi mượn sách không tồn tại", null));
                }else{
                    BorrowBookResponseDTO borrowDto = borrowBook(headerParam, bodyParam);
                    AuthorizationResponseDTO userDto = checkExistUser(bodyParam);
                    if(borrowDto == null){
                        response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin sách bạn nhập không tồn tại", null));
                    }else if(userDto == null){
                        response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin user bạn nhập không tồn tại", null));
                    }
                    else{
                        if(borrowDto.getLimitDate() == null){
                            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin limit date không được để trống", null));
                        }
                        else if(borrowDto.getReturnDate() == (null)){
                            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Thông tin return date không được để trống", null));
                        }
                        else{
                            currentBorrow.setBookName(borrowDto.getBookName());
                            currentBorrow.setUsername(borrowDto.getUsername());
                            currentBorrow.setLimitDate(borrowDto.getLimitDate());
                            currentBorrow.setReturnDate(borrowDto.getReturnDate());
                            borrowService.save(currentBorrow);
                            response = new ResponseMessage(new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),currentBorrow ));
                        }
                    }
                }

            }else{
                response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền cập nhật bản ghi mượn sách ", null));
            }
        }
        return response;
    }

    @DeleteMapping("/loan/delete/{id}")
    public ResponseMessage delete (@PathVariable String id, @RequestHeader Map<String, String > headerParam){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else {
            if (dto.getRoleName().equalsIgnoreCase("admin")) {
                Borrow currentBorrow = borrowService.findById(Long.parseLong(id));
                if(currentBorrow == null){
                    response = new ResponseMessage(new MessageContent(HttpStatus.NOT_FOUND.value(), "Bản ghi mượn sách không tồn tại", null));
                }else{
                    borrowService.remove(currentBorrow);
                    response = new ResponseMessage(new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),"OK" ));
                }
            }else{
                response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền xóa bản ghi mượn sách ", null));
            }
        }

        return response;
    }

    @PostMapping("/loan/return/{id}")
    public ResponseMessage returnBorrow (@RequestHeader Map<String, String > headerParam, @PathVariable String id){
        ResponseMessage response = null;
        AuthorizationResponseDTO dto = authenticateToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập", null));
        }else{
            if(dto.getRoleName().equalsIgnoreCase("admin")) {
                Borrow currentBorrow = borrowService.findById(Long.parseLong(id));
                if(currentBorrow == null){
                    response = new ResponseMessage(new MessageContent(HttpStatus.NOT_FOUND.value(), "Bản ghi không tồn tại", null));
                }else{
                    Map<String, Object> bodyParam = new HashMap<>();
                    String bookName = currentBorrow.getBookName();
                    bodyParam.put("bookName", bookName);
                    BorrowBookResponseDTO returnDto = returnBook(headerParam,bodyParam);
                    bodyParam.remove("bookName");

                    String username = currentBorrow.getUsername();
                    bodyParam.put("username", username);
                    AuthorizationResponseDTO userDto = checkExistUser(bodyParam);

                    if(returnDto == null || userDto == null){
                        response = new ResponseMessage(new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), null));
                    }else{
                        try {
                            Date currentDay = new Date();
                            currentBorrow.setReturnDate(currentDay);
                            borrowService.save(currentBorrow);
                            response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                                    new MessageContent(currentBorrow));
                        } catch (Exception ex) {
                            response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                    new MessageContent(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null));
                        }
                    }
                }
            }else{
                response = new ResponseMessage(new MessageContent(HttpStatus.FORBIDDEN.value(), "Bạn không có quyền tạo bản ghi trả sách ", null));
            }
        }
        return response;
    }

    @PostMapping("/loan/maxBookInTime")
    public ResponseMessage maxBookInTime(@RequestBody Map<String,Object> bodyParam) throws ParseException {
        ResponseMessage response = null;
        String start =(String) bodyParam.get("startDate");
        String end = (String)bodyParam.get("endDate");

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);

        bodyParam.put("startDate", startDate);
        bodyParam.put("endDate", endDate);
        List<Object[]> result = borrowService.maxBookInTime(startDate, endDate);
        if(result == null || result.isEmpty()){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null));
        }else{
            response = new ResponseMessage(new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),result));
        }
        return response;
    }

    @PostMapping("/loan/borrowInTime")
    public ResponseMessage borrowInTime(@RequestBody Map<String,Object> bodyParam) throws ParseException {
        ResponseMessage response = null;
        String start =(String) bodyParam.get("startDate");
        String end = (String)bodyParam.get("endDate");

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);

        bodyParam.put("startDate", startDate);
        bodyParam.put("endDate", endDate);
        List<Object[]> result = borrowService.borrowInTime(startDate, endDate);
        if(result == null || result.isEmpty()){
            response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                    new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null));
        }else{
            response = new ResponseMessage(new MessageContent(HttpStatus.OK.value(), HttpStatus.OK.toString(),result));
        }
        return response;
    }


}
