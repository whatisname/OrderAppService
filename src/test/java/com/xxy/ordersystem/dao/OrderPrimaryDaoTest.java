package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.OrdersPrimary;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/14/2018 1:50 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderPrimaryDaoTest {
    @Autowired
    private OrderPrimaryDao orderPrimaryDao;

    @Test
    public void findAllBySId() {
    }

    @Test
    public void findAllBySIdAndOPState() {
    }

    @Test
    public void findOrdersPrimaryByOPId() {
        OrdersPrimary ordersPrimary = orderPrimaryDao.findOrdersPrimaryByOPId("1");
        log.info("find order: {}", ordersPrimary.toString());
    }

    @Test
    public void findAllByBId() {
    }

    @Test
    public void findAllByBIdAndOPState() {
    }

    @Test
    public void findAllByDId() {
    }

    @Test
    public void findAllByDIdAndOPState() {
    }

    @Test
    public void findAllByAId() {
    }

    @Test
    public void findAllByAIdAndOPState() {
    }
}