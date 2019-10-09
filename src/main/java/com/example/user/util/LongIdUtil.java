package com.example.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongIdUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(LongIdUtil.class);

    private static final SnowflakeIdWorker snowflakeIdWorker =new SnowflakeIdWorker(0,0);

    private LongIdUtil() {

    }

    public static String getLongId(){
       return String.valueOf(snowflakeIdWorker.nextId());
    }
}
