package com.elcom.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BookDTO {

    private Long id;

    private String name;

    public BookDTO(Map<String,Object> map){
        if (map != null && map.size() > 0) {
            if(map.containsKey("id")){
                this.id = (Long) map.get("id");
            }else if(map.containsKey("name")){
                this.name = (String)map.get("name");
            }
        }
    }
}
