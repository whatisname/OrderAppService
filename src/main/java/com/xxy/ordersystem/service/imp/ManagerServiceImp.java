package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.ManagerDao;
import com.xxy.ordersystem.entity.Manager;
import com.xxy.ordersystem.service.intf.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 8/17/2018 5:21 AM
 */
@Service
public class ManagerServiceImp implements ManagerService {
    @Autowired
    private ManagerDao managerDao;


    @Override
    public Manager findManagerById(String managerId) {
        return managerDao.findManagerByMId(managerId);
    }

    @Override
    public Manager findManagerByEmail(String email) {
        return managerDao.findManagerByMEmail(email);
    }

    @Override
    public Manager addManager(Manager manager) {
        return managerDao.save(manager);
    }

    @Override
    public Boolean updateManager(Manager manager) {
        managerDao.save(manager);
        return true;
    }

    @Override
    public Manager deleteManager(Manager manager) {
        managerDao.delete(manager);
        return manager;
    }
}
