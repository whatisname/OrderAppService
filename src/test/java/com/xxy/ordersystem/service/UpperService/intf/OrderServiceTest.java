package com.xxy.ordersystem.service.UpperService.intf;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.service.intf.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.intf
 * @date 7/14/2018 2:08 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodService foodService;

    private String orderId = "1531863039437307773";

    @Test
    public void findOrderByOrderPrimaryId() {
        OrderDTO orderDTO = orderService.findOrderByPrimaryId("10");
        log.info("orderManage dto: {}", orderDTO.toString());
    }

    @Test
    public void findAllOrdersByBoothIdAndState() {
//        List<OrdersPrimary> list = orderService.findAllOrdersByBoothIdAndState("1",
//                OrderStates.FOOD_DELIVERING.getCode());
//        log.info("size: {}", list.size());
    }

    @Test
//    @Transactional
    public void addNewOrder() {
        Map<String, Integer> orderDetailMap = new HashMap<>();
        orderDetailMap.put("1", 5);
        orderDetailMap.put("3", 3);
        String result = orderService.addNewOrder("1", "1", orderDetailMap);
        log.info("result: opid {}", result);
    }

    @Test
    public void updateOrderTo_PREPARING_FOOD() {

        orderService.updateOrderTo_PREPARING_FOOD("1531863039437307773");
    }

    @Test
    public void updateOrderTo_READY_TO_DELIVER() {

        orderService.updateOrderTo_READY_TO_DELIVER("1531863039437307773");
    }

    @Test
    public void updateOrderTo_FOOD_DELIVERING() {

        orderService.updateOrderTo_FOOD_DELIVERING("1531863039437307773", "1");
    }

    @Test
    public void updateOrderTo_CANCELED_BY_USER() {
        orderService.updateOrderTo_CANCELED_BY_USER("1", "取消订单");
    }
}