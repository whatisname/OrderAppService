package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.Quyu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 12:03 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoothDaoTest {
    @Autowired
    private BoothDao boothDao;

    @Test
    public void findAllByBStateEquals() {
        List<Booth> list = boothDao.findAllByBStateEquals(BoothStates.OPEN.getCode());
        log.info("找到开张店铺：{}家", list.size());
    }

    @Test
    public void findAllByBIdIn() {
    }

    @Test
    public void findAllByBIdInAndBStateEquals() {
        List<Booth> list = boothDao.findAllByBIdInAndBStateEquals(Arrays.asList("1","2","4"), BoothStates.OPEN.getCode());
        log.info("找到开张店铺：{}家", list.size());
    }

    @Test
    public void findBoothByBIdEquals() {
        Booth booth = boothDao.findBoothByBIdEquals("1");
        log.info("找到店铺：{}", booth.toString());
    }



}