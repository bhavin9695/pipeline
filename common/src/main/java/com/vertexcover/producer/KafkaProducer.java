package com.vertexcover.producer;

import com.vertexcover.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, User> discardedUserKafkaTemplate;

    public void produceToNewAndRetryUserTopic(String topicName, User data){
        userKafkaTemplate.send(topicName, data);
    }

    public void produceToDiscardedUserTopic(String topicName, User data){
        discardedUserKafkaTemplate.send(topicName, data);
    }

}
