package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.OrderPrimaryDao;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.service.intf.OrderPrimaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/13/2018 10:45 PM
 */
@Service
public class OrderPrimaryServiceImp implements OrderPrimaryService {
    @Autowired
    private OrderPrimaryDao orderPrimaryDao;

    @Override
    public List<OrdersPrimary> findAllByStudentId(String studentId) {

        return orderPrimaryDao.findAllBySId(studentId);
    }

    @Override
    public Page<OrdersPrimary> findAllByStudentId(String studentId, Pageable pageable) {
        return orderPrimaryDao.findAllBySId(studentId, pageable);
    }

    @Override
    public List<OrdersPrimary> findAllByStudentIdAndOrderState(String studentId, Integer orderState) {

        return orderPrimaryDao.findAllBySIdAndOPState(studentId, orderState);
    }

    @Override
    public Page<OrdersPrimary> findAllByStudentIdAndOrderState(String studentId, Integer orderState, Pageable pageable) {

        return orderPrimaryDao.findAllBySIdAndOPState(studentId, orderState, pageable);
    }

    @Override
    public OrdersPrimary findOrdersById(String ordersPrimaryId) {
        return orderPrimaryDao.findOrdersPrimaryByOPId(ordersPrimaryId);
    }

    @Override
    public List<OrdersPrimary> findAllByBoothId(String boothId) {
        return orderPrimaryDao.findAllByBId(boothId);
    }

    @Override
    public Page<OrdersPrimary> findAllByBoothId(String boothId, Pageable pageable) {
        return orderPrimaryDao.findAllByBId(boothId, pageable);
    }

    @Override
    public List<OrdersPrimary> findAllByBoothIdAndOrderState(String boothId, Integer orderState) {
        return orderPrimaryDao.findAllByBIdAndOPState(boothId, orderState);
    }

    @Override
    public List<OrdersPrimary> findAllByDelivererId(String delivererId) {
        return orderPrimaryDao.findAllByDId(delivererId);
    }

    @Override
    public List<OrdersPrimary> findAllByDelivererIdAndOrderState(String delivererId, Integer orderState) {
        return orderPrimaryDao.findAllByDIdAndOPState(delivererId, orderState);
    }

    @Override
    public List<OrdersPrimary> findAllByAddressId(String addressId) {
        return orderPrimaryDao.findAllByAId(addressId);
    }

    @Override
    public List<OrdersPrimary> findAllByAddressIdAndOrderState(String addressId, Integer orderState) {
        return orderPrimaryDao.findAllByAIdAndOPState(addressId, orderState);
    }

    @Override
    public OrdersPrimary saveNewOrdersPrimary(OrdersPrimary ordersPrimary) {
        return orderPrimaryDao.save(ordersPrimary);
    }

    @Override
    public OrdersPrimary updateOrder(OrdersPrimary ordersPrimary) {
        return orderPrimaryDao.save(ordersPrimary);
    }

    @Override
    public Boolean existOrderPrimary(String ordersPrimaryId) {
        OrdersPrimary result = orderPrimaryDao.findOrdersPrimaryByOPId(ordersPrimaryId);
        if (result == null){
            return false;
        }else{
            return true;
        }
    }

}
