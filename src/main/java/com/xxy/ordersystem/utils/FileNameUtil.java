package com.xxy.ordersystem.utils;

import java.util.Random;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/16/2018 1:23 AM
 */
public class FileNameUtil {
    public static synchronized String generateFileName(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() +String.valueOf(number);
    }
}
