package com.vertexcover.config;

public abstract class Constants {
    static final String ZOOKIPPER_IP = "127.0.0.1:9092";
    public static final String STRING_TOPIC = "string_topic";
    static final String STRING_GROUP_ID = "string_group_id";
    public static final String USER_DETAIL_TOPIC = "user_detail_topic";
    public static final String RETRY_USER_DETAIL_TOPIC = "retry_user_detail_topic";
    public static final String DISCARDED_USER_DETAIL_TOPIC = "discarded_user_detail_topic";
    public static final String USER_DETAIL_GROUP_ID = "user_detail_group_id";
    public static final int MAX_RETRY_ATTEMPT = 3;

}
