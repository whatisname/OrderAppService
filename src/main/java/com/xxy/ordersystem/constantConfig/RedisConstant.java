package com.xxy.ordersystem.constantConfig;

/**
 * @author X
 * @package com.xxy.ordersystem.constantConfig
 * @date 8/15/2018 12:16 AM
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE_TIME = 7200; //2 hours
}
