package com.elcom.backend.controller;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMemcachedController {
    @Autowired
    private MemcachedClient memcachedClient;


    @RequestMapping("/test")
    public  String test(){
        System.out.println("======set/get Mode======");
        memcachedClient.set("TEST",3," Elcom1");
        System.out.println("Setting and Reading TEST value:"+memcachedClient.get("TEST"));

        memcachedClient.set("TEST",3," New value is Elcom2");
        System.out.println("Read again TEST value:"+memcachedClient.get("TEST"));

        System.out.println("======add Mode ===");
        memcachedClient.add("TEST",3,"Use ADD Cache added to an existing value");
        System.out.println("Read again TEST value:"+memcachedClient.get("TEST"));

        memcachedClient.add("TEST2",3,"Use ADD Add to new cache key TEST2 in");
        System.out.println("Read again TEST2 value:"+memcachedClient.get("TEST2"));

        System.out.println("===replace Mode===");
        memcachedClient.replace("TEST",3,"Use Replace replace TEST Key corresponding cache value");
        System.out.println("replace Mode reading TEST value:"+memcachedClient.get("TEST"));

        try {
            Thread.sleep(3001);
        }catch (Exception ex){}
        System.out.println("3 Get the cache again after seconds TEST: "+memcachedClient.get("TEST"));

        System.out.println("======delete Mode =====");
        memcachedClient.delete("TEST");
        System.out.println("replace Mode reading TEST value:"+memcachedClient.get("TEST"));

        return "";
    }
}
