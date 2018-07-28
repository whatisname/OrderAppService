package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Deliverer;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 5:32 PM
 */
public interface DelivererService {
    Deliverer findDelivererByDId(String delivererId);
    List<Deliverer> findDelivererByDNameLike(String delivererName);
    Deliverer findDelivererByDEmail(String email);
    Deliverer findDelivererByeAndDPhone(String phone);

    List<Deliverer> findAllDeliverer();

    Deliverer findDelivererByOrderPrimaryId();
}
