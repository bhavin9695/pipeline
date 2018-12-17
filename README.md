# Pipeline

This is multi module demo project of pipeline setup in java with spring boot + kafka + redis with retry or discard kafka consumption . 

# Kafka Setup

Download link : https://kafka.apache.org/quickstart
Follow instruction of link to setup kafka with topics below : 
1) user_detail_topic
2) retry_user_detail_topic
3) discarded_user_detail_topic

Note: Make sure you start zookeeper and kafka server
 
#Redis Setup

Download link : https://redis.io/topics/quickstart
Follow instruction of link to setup redis on your machine and start redis server with port 6379

Note: You can change port number but make sure you also change it same in common module's properties file

#Test
-> After setting up kafka and redis on your machine you can run test class 'PersistenceTest.java' under api module.
 
-> To check retry and discard functionality you can manually shutdown the redis server and check.
 It will do max 3 attempt of retry.
 After that it will discard that record.
 To extend that you can also store it in any other storage to review.


#Other Note
You can add common module as library to extend this project further as done in api module of this project.