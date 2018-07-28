package com.xxy.ordersystem.controller;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.SearchType;
import com.xxy.ordersystem.service.UpperService.intf.SearchService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import com.xxy.ordersystem.viewmessage.viewobject.SearchResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/14/2018 1:33 AM
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    @Autowired
    private BoothService boothService;
    @Autowired
    private FoodService foodService;

    @GetMapping("/all")
    public ResultVO<SearchResultVO> searchAll(
            @RequestParam("content") String content,
            @RequestParam(value = "type", defaultValue = "all") String type,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ){
        //TODO text安全检测
        //TODO food->state
        if (content.equals("")){
            log.error("{} - {}", getClass(), "搜索内容不能为空 ");
            return MessageUtil.error("搜索内容不能为空", null);
        }
        PageRequest request = PageRequest.of(page, size);
        SearchResultVO searchResultVO =  new SearchResultVO();
        if (type.equals(SearchType.ALL.getType())){
            Page<Booth> boothPage = boothService.findAllBoothByNameContains(content, request);
            Page<Food> foodPage = foodService.findAllFoodByNameContains(content, request);

            List<BoothVO> boothVOList = new ArrayList<>();
            for (Booth booth: boothPage.getContent()){
                BoothVO boothVO = new BoothVO();
                BeanUtils.copyProperties(booth, boothVO);
                boothVOList.add(boothVO);
            }
            searchResultVO.setBoothVOList(boothVOList);

            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: foodPage.getContent()){
                FoodVO foodVO = new FoodVO();
                BeanUtils.copyProperties(food, foodVO);
                foodVOList.add(foodVO);
                //查询商店名和状态
                Booth booth = boothService.findBoothById(food.getBId());
                foodVO.setState(booth.getBState());
                foodVO.setBoothName(booth.getBName());
            }
            searchResultVO.setFoodVOList(foodVOList);

        }else if (type.equals(SearchType.BOOTH.getType())){
            Page<Booth> boothPage = boothService.findAllBoothByNameContains(content, request);

            List<BoothVO> boothVOList = new ArrayList<>();
            for (Booth booth: boothPage.getContent()){
                BoothVO boothVO = new BoothVO();
                BeanUtils.copyProperties(booth, boothVO);
                boothVOList.add(boothVO);
            }
            searchResultVO.setBoothVOList(boothVOList);
        }else if(type.equals(SearchType.FOOD.getType())){
            Page<Food> foodPage = foodService.findAllFoodByNameContains(content, request);

            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: foodPage.getContent()){
                FoodVO foodVO = new FoodVO();
                BeanUtils.copyProperties(food, foodVO);
                foodVOList.add(foodVO);
            }
            searchResultVO.setFoodVOList(foodVOList);
        }else {
            log.error("{} - {}", getClass(), "不支持的搜索类型");
            return MessageUtil.error("不支持的搜索类型", null);
        }
        return MessageUtil.successDefault(searchResultVO);
    }

}
