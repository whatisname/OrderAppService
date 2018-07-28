package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.OrdersPrimary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 5:48 PM
 */
public interface OrderPrimaryDao extends JpaRepository<OrdersPrimary, String> {
    List<OrdersPrimary> findAllBySId(String studentId);
    Page<OrdersPrimary> findAllBySId(String studentId, Pageable pageable);
    List<OrdersPrimary> findAllBySIdAndOPState(String studentId, Integer orderState);
    Page<OrdersPrimary> findAllBySIdAndOPState(String studentId, Integer orderState, Pageable pageable);

    OrdersPrimary findOrdersPrimaryByOPId(String ordersPrimaryId);

    List<OrdersPrimary> findAllByBId(String boothId);
    List<OrdersPrimary> findAllByBIdAndOPState(String boothId, Integer orderState);

    List<OrdersPrimary> findAllByDId(String delivererId);
    List<OrdersPrimary> findAllByDIdAndOPState(String delivererId, Integer orderState);

    List<OrdersPrimary> findAllByAId(String addressId);
    List<OrdersPrimary> findAllByAIdAndOPState(String addressId, Integer orderState);

}
