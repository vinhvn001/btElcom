package com.elcom.loan.model.dto;

import com.elcom.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class BorrowBookResponseDTO {


    private String username;

    private String bookName;

    private Date limitDate;

    private Date returnDate;

    public BorrowBookResponseDTO(Map<String, Object> map){

        if (map != null && map.size() > 0) {
            if(map.containsKey("username")){
                this.username = (String) map.get("username");
            }
        }
        if (map != null && map.size() > 0) {
            if(map.containsKey("bookName")){
                this.bookName = (String) map.get("bookName");
            }
        }

        if (map != null && map.size() > 0) {
            if(map.containsKey("limitDate") && map.get("limitDate") != null ){
                this.limitDate = DateUtil.getDateTime((String) map.get("limitDate"), "yyyy-MM-dd HH:mm:ss");
            }
        }
        if (map != null && map.size() > 0) {
            if(map.containsKey("returnDate") && map.get("returnDate") != null ){
                this.returnDate = DateUtil.getDateTime((String) map.get("returnDate"), "yyyy-MM-dd HH:mm:ss");
            }
        }

    }
}
