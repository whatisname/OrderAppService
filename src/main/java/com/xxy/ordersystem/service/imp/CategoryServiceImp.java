package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.CategoryDao;
import com.xxy.ordersystem.entity.Category;
import com.xxy.ordersystem.service.intf.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/11/2018 8:12 PM
 */
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category addNewCategory(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category deleteCategory(Category category) {
        if (this.findCategory(category) != null){
            categoryDao.delete(category);
            return (category);
        }else{
            return null;
        }
    }

    @Override
    public Category deleteCategoryById(String categoryId) {
        Category category = this.findCategoryById(categoryId);
        if (category != null){
            categoryDao.delete(category);
            return (category);
        }else{
            return null;
        }
    }

    @Override
    public Category deleteCategoryByName(String categoryName) {
        Category category = this.findCategoryByName(categoryName);
        if (category != null){
            categoryDao.delete(category);
            return (category);
        }else{
            return null;
        }
    }


    @Override
    public Category findCategoryById(String categoryId) {
        Category category = categoryDao.findByCIdEquals(categoryId);
        if (category == null) {
            categoryDao.delete(category);
            return category;
        }
        else {
            return null;
        }
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        Category category = categoryDao.findByCNameEquals(categoryName);
        if (category == null) {
            return null;
        }
        else {
            categoryDao.delete(category);
            return category;
        }
    }

    @Override
    public Category findCategory(Category category) {
        if (categoryDao.findByCIdEquals(category.getCId()) == null) {
            return null;
        }
        else {
            return category;
        }
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findCategoryById(List<String> categoryIdList) {
        return categoryDao.findByCIdIn(categoryIdList);
    }

    @Override
    public List<Category> findCategoryByName(List<String> categoryNameList) {
        return categoryDao.findByCNameIn(categoryNameList);
    }
}
