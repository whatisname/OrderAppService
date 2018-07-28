package com.xxy.ordersystem.service.intf;


import com.xxy.ordersystem.entity.Category;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service
 * @date 7/11/2018 7:59 PM
 */
public interface CategoryService {

    Category addNewCategory(Category category);

    Category deleteCategory(Category category);
    Category deleteCategoryById(String categoryId);
    Category deleteCategoryByName(String categoryName);

    Category findCategoryById(String categoryId);
    Category findCategoryByName(String categoryName);
    Category findCategory(Category category);

    List<Category> findAllCategories();
    List<Category> findCategoryById(List<String> categoryIdList);
    List<Category> findCategoryByName(List<String> categoryNameList);


}
