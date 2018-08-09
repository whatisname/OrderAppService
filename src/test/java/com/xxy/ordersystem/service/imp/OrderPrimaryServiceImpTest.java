package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.service.intf.OrderPrimaryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/14/2018 1:47 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderPrimaryServiceImpTest {
    @Autowired
    private OrderPrimaryService orderPrimaryService;

    @Test
    public void findAllByStudentId() {
    }

    @Test
    public void findAllByStudentIdAndOrderState() {
    }

    @Test
    public void findOrdersById() {
        OrdersPrimary ordersPrimary = orderPrimaryService.findOrdersById("1");
        log.info("find orderManage: {}", ordersPrimary);
    }

    @Test
    public void findAllByBoothId() {
    }

    @Test
    public void findAllByBoothIdAndOrderState() {
    }

    @Test
    public void findAllByDelivererId() {
    }

    @Test
    public void findAllByDelivererIdAndOrderState() {
    }

    @Test
    public void findAllByAddressId() {
    }

    @Test
    public void findAllByAddressIdAndOrderState() {
    }

    @Test
    public void addNewOrder() {
    }
}