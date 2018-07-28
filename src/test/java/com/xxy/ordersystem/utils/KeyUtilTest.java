package com.xxy.ordersystem.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/16/2018 1:26 AM
 */

@Slf4j
public class KeyUtilTest {

    @Test
    public void generateUniqueKeyId() {
        log.info(KeyUtil.generateUniqueKeyId());
    }
}