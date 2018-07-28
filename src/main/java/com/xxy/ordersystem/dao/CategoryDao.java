package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/11/2018 5:04 PM
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> findByCIdIn(List<String> cIdList);
    List<Category> findByCNameIn(List<String> cNameList);

    Category findByCIdEquals(String categoryCId);
    Category findByCNameEquals(String categoryCName);
}
