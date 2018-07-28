package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 7:41 PM
 */
public interface OrdersDetailDao extends JpaRepository<OrdersDetail, String> {
    OrdersDetail findOrdersDetailByODId(String orderDetailId);
    List<OrdersDetail> findAllByOPId(String orderPrimaryId);
    List<OrdersDetail> findAllByFId(String orderPrimaryId);
}
