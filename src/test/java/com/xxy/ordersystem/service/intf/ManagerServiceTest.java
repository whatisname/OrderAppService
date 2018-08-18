package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Manager;
import com.xxy.ordersystem.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 8/17/2018 5:33 AM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ManagerServiceTest {

    @Autowired
    private ManagerService managerService;
    @Test
    public void addManager() {
        Manager manager = new Manager();
        manager.setMName("admin1");
        manager.setMEmail("admin");
        manager.setMPassword("1");
        manager.setMId(KeyUtil.generateUniqueKeyId());
        managerService.addManager(manager);
    }
}