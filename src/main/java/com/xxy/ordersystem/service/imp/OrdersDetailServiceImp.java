package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.OrdersDetailDao;
import com.xxy.ordersystem.entity.OrdersDetail;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.service.intf.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/13/2018 10:11 PM
 */
@Service
public class OrdersDetailServiceImp implements OrdersDetailService {
    @Autowired
    private OrdersDetailDao ordersDetailDao;

    @Override
    public OrdersDetail findOrdersDetailById(String orderDetailId) {
        return ordersDetailDao.findOrdersDetailByODId(orderDetailId);
    }

    @Override
    public List<OrdersDetail> findAllByOrderPrimaryId(String orderPrimaryId) {
        return ordersDetailDao.findAllByOPId(orderPrimaryId);
    }

    @Override
    public List<OrdersDetail> findAllOrderDetail(OrdersPrimary ordersPrimary) {
        return ordersDetailDao.findAllByOPId(ordersPrimary.getOPId());
    }

    @Override
    public List<OrdersDetail> findAllByFoodId(String orderPrimaryId) {
        return ordersDetailDao.findAllByFId(orderPrimaryId);
    }

    @Override
    public List<OrdersDetail> findAllOrderDetail() {
        return null;
    }

    @Override
    public List<OrdersDetail> saveAllOrderDetails(List<OrdersDetail> ordersDetailList) {
        if (ordersDetailList.size() > 0){
            Iterable<OrdersDetail> iterable = ordersDetailList;
            ordersDetailDao.saveAll(iterable);
        }
        return ordersDetailList;
    }

    @Override
    public List<OrdersDetail> saveNewOrderDetails(List<OrdersDetail> ordersDetailList) {
        if (ordersDetailList != null) {
            if (ordersDetailList.size() != 0) {
                return ordersDetailDao.saveAll(ordersDetailList);
            }else{
                return null;
            }
        }else {
            return null;
        }
    }
}
