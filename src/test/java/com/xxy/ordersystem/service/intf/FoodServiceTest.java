package com.xxy.ordersystem.service.intf;

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

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/12/2018 12:31 AM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    public void findByFoodId() {
        Food result = foodService.findByFoodId("1");
        log.info("查找：{}", result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    public void findByFoodContains() {
//        List<Food> list = foodService.findByFoodNameContains("米");
//        log.info("size: {}", list.size());
//        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAllFood() {
        List<Food> list = foodService.findAllFood();
        log.info("查找到：{}", list.size());
    }

    @Test
    public void findAllFood1() {
        PageRequest request = PageRequest.of(1,2);
        Page<Food> page = foodService.findAllFood(request);
        log.info("Page内找到 {} 个元素。", page.getTotalElements());
        log.info("找到 {} 页。", page.getTotalPages());
    }

    @Test
    public void findAllAvailableFood() {

    }

    @Test
    public void findAllAvailableFood1() {
    }

    @Test
    public void setUnavailable() {
        List<Food> list = foodService.findAllFood();
        int result = foodService.setUnavailable(list);
        log.info("下架结果：{}",result);
    }

    @Test
    public void addNumber() {
        List<Food> list = foodService.findAllFood();
        int result = foodService.addNumber(list, 5);
        log.info("更新 {} 条记录。",result);
    }

    @Test
    public void subNumber() {
        List<Food> list = foodService.findAllFood();
        int result = foodService.subNumber(list, 10);
        log.info("更新 {} 条记录。",result);
    }

    @Test
    public void findAllAvailableByBoothId() {
        List<Food> list = foodService.findAllAvailableByBoothId("1");
        log.info("查找到 {} 条记录: {}",list.size(),list.toString());
    }

    @Test
    public void findAllUnavailableByBoothId() {
        List<Food> list = foodService.findAllUnavailableByBoothId("1");
        log.info("查找到 ({}) 条记录: {}",list.size(),list.toString());
    }

    @Test
    public void findAllByCategoryIdAndBoothId() {
        List<Food> list = foodService.findAllByCategoryIdAndBoothId(2, "1");
        log.info("查找到 ({}) 条记录: {}",list.size(),list.toString());
    }

    @Test
    public void findAllAvailableByCategoryIdAndBoothId() {
        List<Food> list = foodService.findAllAvailableByCategoryIdAndBoothId(2, "1");
        log.info("查找到 ({}) 条记录: {}",list.size(),list.toString());

    }

    @Test
    public void findAllUnavailableByCategoryIdAndBoothId() {
        List<Food> list = foodService.findAllUnavailableByCategoryIdAndBoothId(2, "1");
        log.info("查找到 ({}) 条记录: {}",list.size(),list.toString());

    }

}