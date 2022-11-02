package com.elcom.backend.controller;


import com.elcom.backend.rabbitmq.pubsub.SubscriberSender;
import com.elcom.backend.rabbitmq.rpc.RpcClient;
import com.elcom.backend.rabbitmq.worker.WorkerSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqController.class);

    @Autowired
    WorkerSender workerSender;

    @Autowired
    SubscriberSender subscriberSender;

    @Autowired
    RpcClient rpcClient;

    @RequestMapping(value = "/worker", method = RequestMethod.GET)
    public ResponseEntity<String> sendWorkerMessage(@RequestParam("message") String message,
                                                    @RequestParam("numOfSend") int numOfSend) {
        workerSender.send(message, numOfSend);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public ResponseEntity<String> sendSubscriberMessage(@RequestParam("message") String message,
                                                        @DefaultValue("fanout") @RequestParam("type") String type) {
        if ("direct".equalsIgnoreCase(type)) {
            subscriberSender.sendDirect(message);
        } else {
            subscriberSender.send(message);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/rpc", method = RequestMethod.GET)
    public ResponseEntity<String> sendRpcMessage(@RequestParam("number") int number) {
        rpcClient.send(number);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
