package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.DelivererDao;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.service.intf.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/13/2018 5:35 PM
 */
@Service
public class DelivererServiceImp implements DelivererService {
    @Autowired
    private DelivererDao delivererDao;

    @Override
    public Deliverer findDelivererByDId(String delivererId) {
        return delivererDao.findDelivererByDId(delivererId);
    }

    @Override
    public List<Deliverer> findDelivererByDNameLike(String delivererName) {
        delivererName = "%" + delivererName + "%";
        return delivererDao.findDelivererByDNameLike(delivererName);
    }

    @Override
    public Deliverer findDelivererByDEmail(String email) {
        return delivererDao.findDelivererByDEmail(email);
    }

    @Override
    public Deliverer findDelivererByeAndDPhone(String phone) {
        return delivererDao.findDelivererByDPhone(phone);
    }

    @Override
    public List<Deliverer> findAllDeliverer() {
        return delivererDao.findAll();
    }

    @Override
    public Deliverer findDelivererByOrderPrimaryId() {
        //TODO:
        return null;
    }
}
