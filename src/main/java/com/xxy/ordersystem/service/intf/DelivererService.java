package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Deliverer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<Deliverer> findAllDeliverer(Pageable pageable);

    Deliverer findDelivererByOrderPrimaryId();

    Boolean updateDeliverer(Deliverer deliverer);

    Boolean deleteDelivererById(String delivererId);
    Boolean deleteDeliverer(Deliverer deliverer);

    Deliverer addDeliverer(Deliverer deliverer);
}
