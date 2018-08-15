package com.xxy.ordersystem.controller.user;

import com.xxy.ordersystem.entity.Category;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.service.intf.CategoryService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.CategoryVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.user
 * @date 8/15/2018 7:07 PM
 */
@RestController
@RequestMapping("/user/food")
@Slf4j
public class UserFoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 查找所有商品
     * @return
     */
    @GetMapping("/listAllFood")
    public ResultVO listAllFood(){
        //查询所有上架商品
        List<Food> availableFoodlist = foodService.findAllAvailableFood();

        //查询类目（一次性查询）
        //lambda表达式
        List<String> categoryIdList =
                availableFoodlist.stream()
                        .map(e -> e.getCId())
                        .collect(Collectors.toList());
        List<Category> categoryList = categoryService.findCategoryById(categoryIdList);

        //数据拼装
        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category: categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setCId(category.getCId());
            categoryVO.setCName(category.getCName());

            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: availableFoodlist) {
                if(food.getCId().equals(category.getCId())){
                    FoodVO foodVO = new FoodVO();
                    BeanUtils.copyProperties(food, foodVO);
                    log.info("{}", foodVO.toString());

                    foodVOList.add(foodVO);
                }
            }
            categoryVO.setFoodList(foodVOList);
            categoryVOList.add(categoryVO);
        }

        return MessageUtil.successDefault(categoryVOList);
    }

    /**
     * 查找店内所有商品
     * @param page
     * @param size
     * @param id booth id
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<FoodVO>> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam("id") @NotEmpty(message = "id不能为空") String id){

        PageRequest request = PageRequest.of(page, size);
        //查找商品
        Page<Food> foodPage = foodService.findAllByBoothId(id, request);

        //装配FoodVO
        List<FoodVO> foodVOList = new ArrayList<>();
        for (Food food: foodPage.getContent()){
            FoodVO foodVO = new FoodVO();
            BeanUtils.copyProperties(food, foodVO);
            foodVOList.add(foodVO);
        }

        return MessageUtil.successDefault(foodVOList);
    }
}
