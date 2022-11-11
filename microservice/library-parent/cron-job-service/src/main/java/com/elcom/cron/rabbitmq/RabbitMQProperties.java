package com.elcom.cron.rabbitmq;

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

    @Value("${library.rpc.exchange}")
    public static String LIBRARY_RPC_EXCHANGE;

    @Value("${library.rpc.queue}")
    public static String LIBRARY_RPC_QUEUE;

    @Value("${library.rpc.key}")
    public static String LIBRARY_RPC_KEY;

    @Value("${library.rpc.book.by.author.url}")
    public static String LIBRARY_RPC_BOOK_BY_AUTHOR_URL;

    @Value("${library.rpc.book.by.category.url}")
    public static String LIBRARY_RPC_BOOK_BY_CATEGORY_URL;

    @Value("${library.rpc.book.by.letter.url}")
    public static String LIBRARY_RPC_BOOK_BY_LETTER_URL;

    @Value("${loan.rpc.exchange}")
    public static String LOAN_RPC_EXCHANGE;

    @Value("${loan.rpc.queue}")
    public static String LOAN_RPC_QUEUE;

    @Value("${loan.rpc.key}")
    public static String LOAN_RPC_KEY;

    @Value("${loan.rpc.book.in.time.url}")
    public static String LOAN_RPC_BOOK_IN_TIME_URL;

    @Value("${loan.rpc.borrow.in.time.url}")
    public static String LOAN_RPC_BORROW_IN_TIME_URL;

    @Value("${loan.rpc.expired.borrow.url}")
    public static String LOAN_RPC_EXPIRED_BORROW_URL;




    @Autowired
    public RabbitMQProperties(@Value("${user.rpc.exchange}") String userRpcExchange,
                              @Value("${user.rpc.queue}") String userRpcQueue,
                              @Value("${user.rpc.key}") String userRpcKey,
                              @Value("${user.rpc.authen.url}") String userRpcAuthenUrl,
                              @Value("${library.rpc.exchange}") String libraryRpcExchange,
                              @Value("${library.rpc.queue}") String libraryRpcQueue,
                              @Value("${library.rpc.key}") String libraryRpcKey,
                              @Value("${library.rpc.book.by.author.url}") String libraryRpcBookByAuthorUrl,
                              @Value("${library.rpc.book.by.category.url}") String libraryRpcBookByCategoryUrl,
                              @Value("${library.rpc.book.by.letter.url}") String libraryRpcBookByLetterUrl,
                              @Value("${loan.rpc.book.in.time.url}") String loanRpcBookInTimeUrl,
                              @Value("${loan.rpc.borrow.in.time.url}") String loanRpcBorrowInTimeUrl,
                              @Value("${loan.rpc.exchange}") String loanRpcExchange,
                              @Value("${loan.rpc.queue}") String loanRpcQueue,
                              @Value("${loan.rpc.key}") String loanRpcKey,
                              @Value("${loan.rpc.expired.borrow.url}") String loanRpcExpiredBorrowUrl
                              ){

        USER_RPC_EXCHANGE = userRpcExchange;
        USER_RPC_QUEUE = userRpcQueue;
        USER_RPC_KEY = userRpcKey;
        USER_RPC_AUTHEN_URL = userRpcAuthenUrl;

        LIBRARY_RPC_EXCHANGE = libraryRpcExchange;
        LIBRARY_RPC_QUEUE = libraryRpcQueue;
        LIBRARY_RPC_KEY = libraryRpcKey;

        LIBRARY_RPC_BOOK_BY_AUTHOR_URL = libraryRpcBookByAuthorUrl;
        LIBRARY_RPC_BOOK_BY_CATEGORY_URL = libraryRpcBookByCategoryUrl;
        LIBRARY_RPC_BOOK_BY_LETTER_URL = libraryRpcBookByLetterUrl;

        LOAN_RPC_EXCHANGE = loanRpcExchange;
        LOAN_RPC_QUEUE = loanRpcQueue;
        LOAN_RPC_KEY = loanRpcKey;

        LOAN_RPC_BOOK_IN_TIME_URL = loanRpcBookInTimeUrl;
        LOAN_RPC_BORROW_IN_TIME_URL = loanRpcBorrowInTimeUrl;
        LOAN_RPC_EXPIRED_BORROW_URL = loanRpcExpiredBorrowUrl;


    }


}
