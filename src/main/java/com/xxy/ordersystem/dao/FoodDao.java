package com.xxy.ordersystem.dao;


import com.xxy.ordersystem.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/11/2018 10:56 PM
 */
public interface FoodDao extends JpaRepository<Food, String> {
    List<Food> findByFNumberGreaterThan(int number);
    Page<Food> findAllByFNameContains(String name, Pageable pageable);
    List<Food> findByFNumberEquals(int number);

    List<Food> findAllByBId(String boothId);
    Page<Food> findAllByBId(String boothId, Pageable pageable);
    List<Food> findAllByBIdAndFNumberGreaterThan(String boothId, int number);
    List<Food> findAllByBIdAndFNumberEquals(String boothId, int number);

    List<Food> findAllByCId(int categoryId);
    List<Food> findAllByCIdAndFNumberGreaterThan(int categoryId, int number);
    List<Food> findAllByCIdAndFNumberEquals(int categoryId, int number);

    List<Food> findAllByBIdAndCId(String boothId, int categoryId);
    List<Food> findAllByBIdAndCIdAndFNumberGreaterThan(String boothId, int categoryId, int number);
    List<Food> findAllByBIdAndCIdAndFNumberEquals(String boothId, int categoryId, int number);

    List<Food> findAllByFIdIn(List<String> foodIdList);

}
