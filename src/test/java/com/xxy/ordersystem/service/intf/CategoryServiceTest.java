package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/11/2018 9:29 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void addNewCategory() {
        Category category = new Category("6", "烧烤","2");
        Category result = categoryService.addNewCategory(category);
        log.info("添加："+result.toString());
        Assert.assertEquals(category, result);
    }

    @Test
    public void deleteCategory() {
        Category category = categoryService.findCategoryById("1");
        Category result = categoryService.deleteCategory(category);
        log.info("删除："+result.toString());
        Assert.assertNotNull(null, result);
    }

    @Test
    public void deleteCategoryByTd() {
    }

    @Test
    public void deleteCategoryByType() {
    }

    @Test
    public void deleteCategoryByName() {
    }

    @Test
    public void findCategoryById() {
    }

    @Test
    public void findCategoryByType() {
    }

    @Test
    public void findCategoryByName() {
    }

    @Test
    public void findCategory() {
    }

    @Test
    public void findAllCategories() {
        List<Category> list = categoryService.findAllCategories();
        log.info(String.valueOf(list.size()));
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void findCategoryByType1() {
//        cate
    }

    @Test
    public void findCategoryByName1() {
    }
}