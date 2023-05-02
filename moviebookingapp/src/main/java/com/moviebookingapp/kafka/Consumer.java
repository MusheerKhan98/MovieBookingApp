package com.moviebookingapp.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    Logger log = LoggerFactory.getLogger(Producer.class);
    @KafkaListener(topics = "movie-topic", groupId = "mygroup")
    public void consumeFromTopic(String message){
        log.info("Consumed message "+ message);
    }
}
