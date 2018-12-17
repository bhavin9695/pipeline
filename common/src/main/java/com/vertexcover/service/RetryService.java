package com.vertexcover.service;

import com.vertexcover.config.Constants;
import com.vertexcover.model.User;
import com.vertexcover.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class RetryService {

    @Autowired
    private KafkaProducer kafkaProducer;

    private static final BlockingQueue<User> userFailedBlockingQueue = new ArrayBlockingQueue<>(100);

    public RetryService() {
        new UserFailedProcessor().start();
    }

   private  class UserFailedProcessor extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                User user;
                try {
                    user = userFailedBlockingQueue.take();
                    if (isEligibleForRetry(user)) {
                        user.setRetryAttemptCount(user.getRetryAttemptCount() + 1);
                        kafkaProducer.produceToNewAndRetryUserTopic(Constants.RETRY_USER_DETAIL_TOPIC, user);
                    } else {
                        kafkaProducer.produceToDiscardedUserTopic(Constants.DISCARDED_USER_DETAIL_TOPIC, user);
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Something went wrong : " + ex);
                }
            }
        }
    }

    private boolean isEligibleForRetry(User user) {
        return user.getRetryAttemptCount() < Constants.MAX_RETRY_ATTEMPT;
    }

    public void doRetry(User user){
        try {
            userFailedBlockingQueue.put(user);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong : " + e);
        }
    }
}
