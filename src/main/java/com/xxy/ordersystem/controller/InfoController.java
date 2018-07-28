package com.xxy.ordersystem.controller;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/22/2018 1:51 PM
 */
@RestController
@Slf4j
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private BoothService boothService;

    @GetMapping("recommend")
    public ResultVO<List<BoothVO>> recommend(){

        //查找所有上架商品
        List<Food> availableFoodList = foodService.findAllAvailableFood();

        //查找对应booth
        List<String> boothIdList =
                availableFoodList.stream().map(e -> e.getBId()).collect(Collectors.toList());

        List<Booth> boothList = boothService.findAllBoothByIdIn(boothIdList);

        List<BoothVO> boothVOList = new ArrayList<>();
        for (Booth booth: boothList){
            BoothVO boothVO = new BoothVO();
            BeanUtils.copyProperties(booth, boothVO);
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));

            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: availableFoodList){
                if(food.getBId().equals(booth.getBId())){
                    FoodVO foodVO = new FoodVO();
                    BeanUtils.copyProperties(food, foodVO);
                    foodVOList.add(foodVO);
                }
            }
            boothVO.setFoodVOList(foodVOList);
            boothVOList.add(boothVO);
        }
        log.info("boothVOList.size: {}", boothVOList.size());
        //装配
        return MessageUtil.successDefault(boothVOList);
    }

    @GetMapping("/adv")
    public ResultVO<List<String>> ads(){
        //TODO
        List<String> advList = new ArrayList<>();
        advList.add("1532572959873819060.png");
        advList.add("1532572933735843917.jpg");
        advList.add("1532572943981958377.png");
        advList.add("1532572953102957760.png");

        return MessageUtil.successDefault(advList);
    }
}
