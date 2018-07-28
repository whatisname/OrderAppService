package com.xxy.ordersystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author X
 * @package com.xxy.ordersystem
 * @date 7/11/2018 2:04 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "iii";
        String pasword = "111";

        //{}占位符
        log.info("info... name: {}, password: {}", name, pasword);
        log.debug("debug...");
        log.info("info...");
        log.error("error...");
        log.warn("warn....");
        log.trace("trace...");
    }
}
