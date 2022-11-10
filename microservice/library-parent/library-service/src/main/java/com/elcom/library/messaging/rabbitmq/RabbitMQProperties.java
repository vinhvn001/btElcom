package com.elcom.library.messaging.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProperties {
    // User service
    @Value("${user.rpc.exchange}")
    public static String USER_RPC_EXCHANGE;

    @Value("${user.rpc.queue}")
    public static String USER_RPC_QUEUE;

    @Value("${user.rpc.key}")
    public static String USER_RPC_KEY;

    @Value("${user.rpc.authen.url}")
    public static String USER_RPC_AUTHEN_URL;

    @Value("${elastic.rpc.exchange}")
    public static String ELASTIC_RPC_EXCHANGE;

    @Value("${elastic.rpc.queue}")
    public static String ELASTIC_RPC_QUEUE;

    @Value("${elastic.rpc.key}")
    public static String ELASTIC_RPC_KEY;

    @Value("${elastic.rpc.save.book.url}")
    public static String ELASTIC_RPC_SAVE_BOOK_URL;

    @Value("${elastic.rpc.update.book.url}")
    public static String ELASTIC_RPC_UPDATE_BOOK_URL;

    @Value("${elastic.rpc.delete.book.url}")
    public static String ELASTIC_RPC_DELETE_BOOK_URL;

    @Value("${elastic.rpc.save.author.url}")
    public static String ELASTIC_RPC_SAVE_AUTHOR_URL;

    @Value("${elastic.rpc.update.author.url}")
    public static String ELASTIC_RPC_UPDATE_AUTHOR_URL;

    @Value("${elastic.rpc.delete.author.url}")
    public static String ELASTIC_RPC_DELETE_AUTHOR_URL;

    @Value("${elastic.rpc.save.category.url}")
    public static String ELASTIC_RPC_SAVE_CATEGORY_URL;

    @Value("${elastic.rpc.update.category.url}")
    public static String ELASTIC_RPC_UPDATE_CATEGORY_URL;

    @Value("${elastic.rpc.delete.category.url}")
    public static String ELASTIC_RPC_DELETE_CATEGORY_URL;

    @Autowired
    public RabbitMQProperties(@Value("${user.rpc.exchange}") String userRpcExchange,
                              @Value("${user.rpc.queue}") String userRpcQueue,
                              @Value("${user.rpc.key}") String userRpcKey,
                              @Value("${user.rpc.authen.url}") String userRpcAuthenUrl,
                              @Value("${elastic.rpc.queue}") String elasticRpcQueue,
                              @Value("${elastic.rpc.key}") String elasticRpcKey,
                              @Value("${elastic.rpc.exchange}") String elasticRpcExchange,
                              @Value("${elastic.rpc.save.book.url}") String elasticRpcSaveBookUrl,
                              @Value("${elastic.rpc.update.book.url}") String elasticRpcUpdateBookUrl,
                              @Value("${elastic.rpc.delete.book.url}") String elasticRpcDeleteBookUrl,
                              @Value("${elastic.rpc.save.author.url}") String elasticRpcSaveAuthorUrl,
                              @Value("${elastic.rpc.update.author.url}") String elasticRpcUpdateAuthorUrl,
                              @Value("${elastic.rpc.delete.author.url}") String elasticRpcDeleteAuthorUrl,
                              @Value("${elastic.rpc.save.category.url}") String elasticRpcSaveCategoryUrl,
                              @Value("${elastic.rpc.update.category.url}") String elasticRpcUpdateCategoryUrl,
                              @Value("${elastic.rpc.delete.category.url}") String elasticRpcDeleteCategoryUrl){

        USER_RPC_EXCHANGE = userRpcExchange;
        USER_RPC_QUEUE = userRpcQueue;
        USER_RPC_KEY = userRpcKey;
        USER_RPC_AUTHEN_URL = userRpcAuthenUrl;

        ELASTIC_RPC_EXCHANGE = elasticRpcExchange;
        ELASTIC_RPC_QUEUE = elasticRpcQueue;
        ELASTIC_RPC_KEY = elasticRpcKey;
        ELASTIC_RPC_SAVE_BOOK_URL = elasticRpcSaveBookUrl;
        ELASTIC_RPC_UPDATE_BOOK_URL = elasticRpcUpdateBookUrl;
        ELASTIC_RPC_DELETE_BOOK_URL = elasticRpcDeleteBookUrl;

        ELASTIC_RPC_SAVE_AUTHOR_URL = elasticRpcSaveAuthorUrl;
        ELASTIC_RPC_UPDATE_AUTHOR_URL = elasticRpcUpdateAuthorUrl;
        ELASTIC_RPC_DELETE_AUTHOR_URL = elasticRpcDeleteAuthorUrl;

        ELASTIC_RPC_SAVE_CATEGORY_URL = elasticRpcSaveCategoryUrl;
        ELASTIC_RPC_UPDATE_CATEGORY_URL = elasticRpcUpdateCategoryUrl;
        ELASTIC_RPC_DELETE_CATEGORY_URL = elasticRpcDeleteCategoryUrl;
    }


}
