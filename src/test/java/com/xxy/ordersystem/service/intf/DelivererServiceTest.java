package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.dao.DelivererDao;
import com.xxy.ordersystem.entity.Deliverer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 5:40 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DelivererServiceTest {
    @Autowired
    private DelivererDao delivererDao;

    @Test
    public void findDelivererByDId() {
        Deliverer deliverer = delivererDao.findDelivererByDId("1");
        log.info("找到：{}", deliverer);
    }

    @Test
    public void findDelivererByDNameLike() {
        List<Deliverer> list = delivererDao.findDelivererByDNameLike("山");
        log.info("找到：{}条", list.size());
    }

    @Test
    public void findDelivererByDEmail() {
    }

    @Test
    public void findDelivererByeAndDPhone() {
    }

    @Test
    public void findAllDeliverer() {
    }

    @Test
    public void findDelivererByOrderPrimaryId() {
    }
}