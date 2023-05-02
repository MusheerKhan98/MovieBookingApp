package com.moviebookingapp.kafka;

import com.moviebookingapp.controller.MovieController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    Logger log = LoggerFactory.getLogger(Producer.class);

    public static final String topic = "movie-topic";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void publishToTopic(String message){
        log.info("Publishing to topic"+topic);
        this.kafkaTemplate.send(topic, message);
    }
}
