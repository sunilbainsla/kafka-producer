package com.sunilbainsla.kafka.producer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service")
public class KafkaProducerController {

    private final Logger LOGGER = LogManager.getLogger(KafkaProducerController.class);

    @Autowired
    Sender sender;

    @PostMapping("/send/create")
    public void sendCreation(@RequestBody String message) {
        System.out.println("POST FIRE AND FORGET send message: " + message);
        sender.send(message);

    }


}
