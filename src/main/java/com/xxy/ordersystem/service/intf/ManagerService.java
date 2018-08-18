package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Manager;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 8/17/2018 5:17 AM
 */
public interface ManagerService {
    Manager findManagerById(String managerId);
    Manager findManagerByEmail(String email);
    Manager addManager(Manager manager);
    Boolean updateManager(Manager manager);
    Manager deleteManager(Manager manager);
}
