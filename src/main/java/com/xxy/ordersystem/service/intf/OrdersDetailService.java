package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.OrdersDetail;
import com.xxy.ordersystem.entity.OrdersPrimary;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 9:51 PM
 */
public interface OrdersDetailService {
    OrdersDetail findOrdersDetailById(String orderDetailId);

    List<OrdersDetail> findAllByOrderPrimaryId(String orderPrimaryId);
    List<OrdersDetail> findAllOrderDetail(OrdersPrimary ordersPrimary);

    List<OrdersDetail> findAllByFoodId(String orderPrimaryId);

    List<OrdersDetail> findAllOrderDetail();

    List<OrdersDetail> saveAllOrderDetails(List<OrdersDetail> ordersDetailList);

    List<OrdersDetail> saveNewOrderDetails(List<OrdersDetail> ordersDetailList);

}
