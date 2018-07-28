package com.xxy.ordersystem.service.UpperService.imp;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.service.UpperService.intf.SearchService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import com.xxy.ordersystem.viewmessage.viewobject.SearchResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.imp
 * @date 7/13/2018 11:58 PM
 */

@Service
public class SearchServiceImp implements SearchService {
    @Autowired
    private FoodService foodService;

    @Autowired
    private BoothService boothService;

//    @Override
//    public SearchResultVO searchAll(String searchText, Integer page, Integer size) {
//
//        SearchResultVO searchResultVO = new SearchResultVO();
//        PageRequest pageRequest = new
//        Page<Booth> boothPage = new PageImpl<>()
//        //查找商店
//        List<BoothVO> boothVOList = new ArrayList<>();
//        List<Booth> boothList = boothService.findByBoothNameContains(searchText);
//        for (Booth booth: boothList){
//            BoothVO boothVO = new BoothVO();
//            BeanUtils.copyProperties(booth, boothVO);
//            boothVOList.add(boothVO);
//        }
//        searchResultVO.setBoothList(boothVOList);
//
//        //查找商品
//        List<FoodVO> foodVOList = new ArrayList<>();
//        List<Food> foodList = foodService.findByFoodNameContains(searchText);
//        for (Food food: foodList){
//            FoodVO foodVO = new FoodVO();
//            BeanUtils.copyProperties(food, foodVO);
//            foodVOList.add(foodVO);
//        }
//        searchResultVO.setFoodVOList(foodVOList);
//
//        return searchResultVO;
//    }

    @Override
    public SearchResultVO searchBooth(String searchText, Pageable pageable) {
        return null;
    }

    @Override
    public SearchResultVO searchFood(String searchText, Pageable pageable) {
        return null;
    }
}
