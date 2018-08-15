package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.DelivererDao;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.service.intf.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Deliverer> findAllDeliverer(Pageable pageable) {
        return delivererDao.findAll(pageable);
    }

    @Override
    public Deliverer findDelivererByOrderPrimaryId() {
        //TODO:
        return null;
    }

    @Override
    public Boolean updateDeliverer(Deliverer deliverer) {
        delivererDao.save(deliverer);

        return true;
    }

    @Override
    public Boolean deleteDelivererById(String delivererId) {
        Deliverer deliverer = this.findDelivererByDId(delivererId);
        if (deliverer == null){
            return false;
        }else {
            return this.deleteDeliverer(deliverer);
        }
    }

    @Override
    public Boolean deleteDeliverer(Deliverer deliverer) {
        delivererDao.delete(deliverer);
        return true;
    }

    @Override
    public Deliverer addDeliverer(Deliverer deliverer) {
        Deliverer result = delivererDao.save(deliverer);
        return result;
    }
}
