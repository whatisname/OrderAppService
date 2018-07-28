package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.OrdersPrimary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 5:56 PM
 */
public interface OrderPrimaryService {

    List<OrdersPrimary> findAllByStudentId(String studentId);
    Page<OrdersPrimary> findAllByStudentId(String studentId, Pageable pageable);
    List<OrdersPrimary> findAllByStudentIdAndOrderState(String studentId, Integer orderState);
    Page<OrdersPrimary> findAllByStudentIdAndOrderState(String studentId, Integer orderState,  Pageable pageable);

    OrdersPrimary findOrdersById(String ordersPrimaryId);

    List<OrdersPrimary> findAllByBoothId(String boothId);
    List<OrdersPrimary> findAllByBoothIdAndOrderState(String boothId, Integer orderState);

    List<OrdersPrimary> findAllByDelivererId(String delivererId);
    List<OrdersPrimary> findAllByDelivererIdAndOrderState(String delivererId, Integer orderState);

    List<OrdersPrimary> findAllByAddressId(String addressId);
    List<OrdersPrimary> findAllByAddressIdAndOrderState(String addressId, Integer orderState);

    OrdersPrimary saveNewOrdersPrimary(OrdersPrimary ordersPrimary);

    OrdersPrimary updateOrder(OrdersPrimary ordersPrimary);

    Boolean existOrderPrimary(String ordersPrimaryId);
}
