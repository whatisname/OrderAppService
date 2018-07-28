package com.xxy.ordersystem.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 3:38 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressDaoTest {
    @Autowired
    AddressDao addressDao;

    @Test
    public void findAddressByAId() {
        log.info("{}", addressDao.findAddressByAId("1"));
    }

    @Test
    public void findAddressBySIdAndADefaultEquals() {
    }

    @Test
    public void findAllBySId() {
    }

    @Test
    public void findAllByAQuyu() {
    }
}