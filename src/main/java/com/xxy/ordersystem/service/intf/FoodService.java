package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/11/2018 11:48 PM
 */

public interface FoodService {
    Food findByFoodId(String foodId);

    /**
     * 名字含foodName的菜品
     * @param foodName
     * @return
     */
    Page<Food> findAllFoodByNameContains(String foodName, Pageable pageable);

    /**
     * 查询所有菜品（上、下架）
     * @return
     */
    List<Food> findAllFood();

    /**
     * 查询所有商品（上、下架），分页
     * @return
     */
    Page<Food> findAllFood(Pageable pageable);

    /**
     * 查找所有上架商品
     * @return
     */
    List<Food> findAllAvailableFood();

    /**
     * 查找所有下架商品
     * @return
     */
    List<Food> findAllUnavailableFood();

    /**
     * 查找所有上架商品，分页
     * @param pageable
     * @return
     */
    Page<Food> findAllAvailableFood(Pageable pageable);

    List<Food> findAllByCategoryId(String categoryId);
    List<Food> findAllAvailableByCategoryId(String categoryId);
    List<Food> findAllUnavailableByCategoryId(String categoryId);

    //买家查询

    /**
     * 查询商家下的所有商品
     * @param boothId
     * @return
     */
    List<Food> findAllByBoothId(String boothId);
    Page<Food> findAllByBoothId(String boothId, Pageable pageable);

    /**
     * 查询商家下的所有上架商品
     * @param boothId
     * @return
     */
    List<Food> findAllAvailableByBoothId(String boothId);

    /**
     * 查询商家下的所有下架商品
     * @param boothId
     * @return
     */
    List<Food> findAllUnavailableByBoothId(String boothId);

    /**
     * 查询商家下的某一分类的所有商品
     * @param boothId
     * @return
     */
    List<Food> findAllByCategoryIdAndBoothId(int categoryId, String boothId);

    /**
     * 查询商家下的某一分类的所有上架商品
     * @param boothId
     * @return
     */
    List<Food> findAllAvailableByCategoryIdAndBoothId(int categoryId, String boothId);

    /**
     * 查询商家下的某一分类的所有下架商品
     * @param boothId
     * @return
     */
    List<Food> findAllUnavailableByCategoryIdAndBoothId(int categoryId, String boothId);

    /**
     * 按照id列表查询所有商品
     * @param foodIdList {@link List<String>}
     * @return {@link List<Food>}
     */
    List<Food> findAllFoodById(List<String> foodIdList);

    List<Food> updateFood(List<Food> foodList);
    Boolean updateFood(Food food);



    //==============================库存操作====================================

    /**
     * 清零库存(下架)
     * @param foodList
     * @return 0: list为空
     */
    int setUnavailable(List<Food> foodList);

    /**
     * 加库存
     * @param foodList
     * @param number
     * @return 0: list为空，更新失败或不存在foodid
     */
    int addNumber(List<Food> foodList, int number);

    /**
     * 减库存
     * @param foodList
     * @param number
     * @return 0: list为空，更新失败或不存在foodid
     */
    int subNumber(List<Food> foodList, int number);

    /**
     *
     * @param cartMap {@link Map<String, Integer>} Map<foodId, number>
     * @return 0: list为空，更新失败或不存在foodid
     */
    int addNumber(Map<String, Integer> cartMap);

    //============================================================================

}
