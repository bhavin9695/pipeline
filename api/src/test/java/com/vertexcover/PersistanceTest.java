package com.vertexcover;

;
import com.vertexcover.config.Constants;
import com.vertexcover.model.User;
import com.vertexcover.producer.KafkaProducer;
import com.vertexcover.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class PersistanceTest extends AbstractTest {


    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private UserService userService;


    @Test
    public void testPipeline(){
        User user = new User();
        user.setUserName("bhavin");
        user.setDept("Technical");
        user.setName("Bhavin Hirpara");
        kafkaProducer.produceToNewAndRetryUserTopic(Constants.USER_DETAIL_TOPIC, user);
        User user1 = userService.findById("bhavin");
        Assert.assertNotNull(user1);
        Assert.assertEquals(user,user1);
    }


}
