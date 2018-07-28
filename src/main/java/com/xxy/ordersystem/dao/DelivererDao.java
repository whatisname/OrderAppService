package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Deliverer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 5:27 PM
 */
public interface DelivererDao extends JpaRepository<Deliverer, String> {
    Deliverer findDelivererByDId(String delivererId);
    List<Deliverer> findDelivererByDNameLike(String delivererName);
    Deliverer findDelivererByDEmail(String email);
    Deliverer findDelivererByDPhone(String phone);
}
