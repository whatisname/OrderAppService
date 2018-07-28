package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/11/2018 5:07 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryDaoTest {
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void findOneTest(){
//        Category category =  categoryDao.getOne(1); // ??
        Category category =  categoryDao.findByCIdEquals("2");
        log.info("找到："+category.toString());

//        Category result = new Category("水果", 4);
//        Assert.assertNotEquals("匹配失败", result, category);
    }

    @Test
    @Transactional
    public void saveTest(){
        Category category = new Category("6","1113", "3");
        categoryDao.save(category);
    }

    @Test
    public void UpdateTest(){
        Category category = categoryDao.findById(2).get();
        category.setCName("烧烤");
        categoryDao.save(category);
    }

    @Test
    public void findByCTypeInTest() {
        List<String> list = Arrays.asList("2","3","4");

        List<Category> result = categoryDao.findByCIdIn(list);
        log.info(String.valueOf(result.size()));
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCNameInTest() {
        List<String> list = Arrays.asList("饮料","1","水果");

        List<Category> result = categoryDao.findByCNameIn(list);
        log.info(String.valueOf(result.size()));
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCNameEqualsTest(){
        Category category = categoryDao.findByCNameEquals("烧烤");
        log.info(category.toString());
        Assert.assertNotNull(category);
    }

    @Test
    public void findByCTypeEquals() {
        Category category = categoryDao.findByCIdEquals("10");
//        Category category = categoryDao.findById(10).get();
//        log.info(categoryDao.findById(10).toString());
        log.info(category.toString());
        Assert.assertNotNull(category);
    }
}