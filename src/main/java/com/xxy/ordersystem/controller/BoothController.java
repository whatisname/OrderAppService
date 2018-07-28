package com.xxy.ordersystem.controller;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;
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
 * @date 7/12/2018 11:33 PM
 */
@RestController
@RequestMapping("/booth")
@Slf4j
public class BoothController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private BoothService boothService;

    @GetMapping("/list")
    public ResultVO<List<BoothVO>> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1")Integer state){

        PageRequest request = PageRequest.of(page, size);
        Page<Booth> boothPage = null;
        //查找商家
        if (state == -1){ // 所有店铺
            boothPage = boothService.findAllBooth(request);
        }else if (state == BoothStates.OPEN.getCode()){ //营业中店铺
            boothPage = boothService.findAllOpenBooth(request);
        }else if(state == BoothStates.REST.getCode()){ //休息中店铺
            boothPage = boothService.findAllRestBooth(request);
        }else if(state == BoothStates.CLOSE.getCode()){ //已关闭店铺
            boothPage = boothService.findAllCloseBooth(request);
        }else{
            log.error("{} - {}", getClass(), ExceptionStates.PARAM_ERROR);
            throw new SaleException(ExceptionStates.PARAM_ERROR);
        }
        //装配BoothVO
        List<BoothVO> boothVOList = new ArrayList<>();
        for (Booth booth: boothPage.getContent()){
            BoothVO boothVO = new BoothVO();
            BeanUtils.copyProperties(booth, boothVO);
            boothVOList.add(boothVO);
        }

        return MessageUtil.successDefault(boothVOList);
    }

    //传入booth id返回商店详情
    @GetMapping("/detail")
    public ResultVO<BoothVO> detail(
            @RequestParam("id") String bid
    ){
        BoothVO boothVO = new BoothVO();
        Booth booth = boothService.findBoothById(bid);
        if (booth == null){
            log.error("{} - {}", getClass(), "bid不存在");
            return MessageUtil.error("bid不存在", null);
        }else {
            BeanUtils.copyProperties(booth, boothVO);
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));
            //查找所有food
            List<Food> foodList = foodService.findAllByBoothId(bid);
            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: foodList){
                FoodVO foodVO = new FoodVO();
                BeanUtils.copyProperties(food, foodVO);
                foodVOList.add(foodVO);
            }
            boothVO.setFoodVOList(foodVOList);
            return MessageUtil.successDefault(boothVO);
        }
    }

    //传入food id返回商店详情
    @GetMapping("/detailByFid")
    public ResultVO<BoothVO> detailByFid(
            @RequestParam("fid") String fid
    ){
        //获取booth id
        Food food1 = foodService.findByFoodId(fid);
        if (food1 == null){
            log.error("{} - {}", getClass(), "fid不存在");
            return MessageUtil.error("fid不存在", null);
        }
        String bid = food1.getBId();
        BoothVO boothVO = new BoothVO();
        Booth booth = boothService.findBoothById(bid);
        if (booth == null){
            log.error("{} - {}", getClass(), "bid不存在");
            return MessageUtil.error("bid不存在", null);
        }else {
            BeanUtils.copyProperties(booth, boothVO);
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));
            //查找所有food
            List<Food> foodList = foodService.findAllByBoothId(bid);
            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food: foodList){
                FoodVO foodVO = new FoodVO();
                BeanUtils.copyProperties(food, foodVO);
                foodVOList.add(foodVO);
            }
            boothVO.setFoodVOList(foodVOList);
            return MessageUtil.successDefault(boothVO);
        }
    }

}
