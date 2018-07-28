package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Food;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;


/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/11/2018 11:07 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FoodDaoTest {

    @Autowired
    private FoodDao foodDao;

    @Test
    public void findByFNumberGreaterThan() {
        List<Food> list = foodDao.findByFNumberGreaterThan(0);
        log.info("size: {}", list.size());
        Assert.assertThat(list.size(), greaterThan(0));
    }

    @Test
    public void findAllByFNameContains() {
        Page<Food> page = foodDao.findAllByFNameContains("米线", PageRequest.of(0, 10));
        log.info("size: {}", page.getTotalElements());
    }

    @Test
    public void saveTest(){
        Food food = new Food("3", "过桥米线",new BigDecimal(3.4));
        Food result = foodDao.save(food);
        log.info("添加: "+result.toString());
        Assert.assertEquals(food, result);
    }

    @Test
    public void findByFNumberEquals() {
        List<Food> list = foodDao.findByFNumberEquals(0);
        log.info("size: {}", list.size());
//        Assert.assertThat(list.size(), greaterThan(0));
    }

    @Test
    public void findAll() {
        List<Food> list = foodDao.findAll();
        log.info("size: {}", list.size());
//        Assert.assertThat(list.size(), greaterThan(0));
    }

    @Test
    public void findAllByBId() {
        List<Food> list = foodDao.findAllByBId("1");
        log.info("size: {}", list.size());
    }

    @Test
    public void findAllByCId() {
        List<Food> list = foodDao.findAllByCId(3);
        log.info("size: {}", list.size());
    }
}