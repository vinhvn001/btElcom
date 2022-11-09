package com.elcom.loan.messaging.rabbitmq;

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

    @Value("${user.rpc.exist.url}")
    public static String USER_RPC_EXIST_URL;

    @Value("${library.rpc.borrow.url}")
    public static String LIBRARY_RPC_BORROW_URL;

    @Value("${library.rpc.return.url}")
    public static String LIBRARY_RPC_RETURN_URL;
    @Value("${library.rpc.exchange}")
    public static String LIBRARY_RPC_EXCHANGE;

    @Value("${library.rpc.queue}")
    public static String LIBRARY_RPC_QUEUE;

    @Value("${library.rpc.key}")
    public static String LIBRARY_RPC_KEY;

    @Value("${loan.rpc.exchange}")
    public static String LOAN_RPC_EXCHANGE;

    @Value("${loan.rpc.queue}")
    public static String LOAN_RPC_QUEUE;

    @Value("${loan.rpc.key")
    public static String LOAN_RPC_KEY;


    @Autowired
    public RabbitMQProperties(@Value("${user.rpc.exchange}") String userRpcExchange,
                              @Value("${user.rpc.queue}") String userRpcQueue,
                              @Value("${user.rpc.key}") String userRpcKey,
                              @Value("${user.rpc.authen.url}") String userRpcAuthenUrl,
                              @Value("${user.rpc.exist.url}") String userRpcExistUrl,
                              @Value("${loan.rpc.exchange}") String loanRpcExchange,
                              @Value("${loan.rpc.queue}") String loanRpcQueue,
                              @Value("${loan.rpc.key}") String loanRpcKey,
                              @Value("${library.rpc.exchange}") String libraryRpcExchange,
                              @Value("${library.rpc.queue}") String libraryRpcQueue,
                              @Value("${library.rpc.key}") String libraryRpcKey,
                              @Value("${library.rpc.borrow.url}") String libraryRpcBorrowUrl,
                              @Value("${library.rpc.return.url}") String libraryRpcReturnUrl){

        USER_RPC_EXCHANGE = userRpcExchange;
        USER_RPC_QUEUE = userRpcQueue;
        USER_RPC_KEY = userRpcKey;
        USER_RPC_AUTHEN_URL = userRpcAuthenUrl;
        USER_RPC_EXIST_URL = userRpcExistUrl;


        LOAN_RPC_EXCHANGE = loanRpcExchange;
        LOAN_RPC_QUEUE = loanRpcQueue;
        LOAN_RPC_KEY = loanRpcKey;

        LIBRARY_RPC_EXCHANGE = libraryRpcExchange;
        LIBRARY_RPC_QUEUE = libraryRpcQueue;
        LIBRARY_RPC_KEY = libraryRpcKey;
        LIBRARY_RPC_BORROW_URL = libraryRpcBorrowUrl;
        LIBRARY_RPC_RETURN_URL = libraryRpcReturnUrl;
    }


}
