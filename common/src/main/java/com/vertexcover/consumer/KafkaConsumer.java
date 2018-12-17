package com.vertexcover.consumer;

import com.vertexcover.config.Constants;
import com.vertexcover.model.User;
import com.vertexcover.service.RetryService;
import com.vertexcover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private RetryService retryService;


    @KafkaListener(topics = {Constants.USER_DETAIL_TOPIC, Constants.RETRY_USER_DETAIL_TOPIC}, groupId = Constants.USER_DETAIL_GROUP_ID,
            containerFactory = "userKafkaListenerFactory")
    public void consumeUser(User user) {
        try{
            System.out.println("Consumed User Message: " + user);
            userService.save(user);
        }catch (Exception e){
            retryService.doRetry(user);
        }

    }

    @KafkaListener(topics = {Constants.DISCARDED_USER_DETAIL_TOPIC}, groupId = Constants.USER_DETAIL_GROUP_ID,
            containerFactory = "userKafkaListenerFactory")
    public void consumeDiscardedUserData(User user) {
        try{
            System.out.println("Consumed discarded user message: " + user);
            // we can do further processing with discarded user. Like store it in any other permanent storage to review.
        }catch (Exception e){
            System.out.println("Something went wrong : "+ e);
        }

    }
}
