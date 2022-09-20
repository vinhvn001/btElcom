package elcom.ex1.librarybooks.controller;

import elcom.ex1.librarybooks.message.rabbitmq.WorkerSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/worker", method = RequestMethod.GET)
    public ResponseEntity<String> sendWorkerMessage(@RequestParam("message") String message,
                                                    @RequestParam("numOfSend") int numOfSend) {
        workerSender.send(message, numOfSend);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
