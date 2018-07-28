package com.xxy.ordersystem.service.imp;

import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import com.xxy.ordersystem.dao.FoodDao;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.intf.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/12/2018 12:24 AM
 */
@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    FoodDao foodDao;

    @Override
    public Food findByFoodId(String foodId) {
        Optional optional = foodDao.findById(foodId);
        if(optional.isPresent()){
            return (Food)optional.get();
        }else {
            return null;
        }
    }

    @Override
    public Page<Food> findAllFoodByNameContains(String foodName, Pageable pageable) {
        return foodDao.findAllByFNameContains(foodName, pageable);

    }

    @Override
    public List<Food> findAllFood() {
        List<Food> list = foodDao.findAll();
        if (list.size() == 0){
            return null;
        }else {
            return list;
        }
    }

    @Override
    public Page<Food> findAllFood(Pageable pageable) {

        return foodDao.findAll(pageable);
    }

    @Override
    public List<Food> findAllAvailableFood() {

        return foodDao.findByFNumberGreaterThan(0);
    }

    @Override
    public List<Food> findAllUnavailableFood() {

        return foodDao.findByFNumberEquals(0);
    }

    @Override
    public Page<Food> findAllAvailableFood(Pageable pageable) {

        return foodDao.findAll(pageable);
    }

    @Override
    public int setUnavailable(List<Food> foodList) {
        if(foodList.size() == 0){
            return 0;
        }else {
            for (Food food: foodList ) {
                food.setFNumber(0);
            }
            Iterable<Food> iterable = foodList;
            foodDao.saveAll(iterable);
            return foodList.size();
        }
    }

    @Override
    @Transactional
    public int addNumber(List<Food> foodList, int number) {
        if(foodList.size() == 0){
            return 0;
        }else {
            for (Food food: foodList ) {
                food.setFNumber(food.getFNumber()+number);
            }
            Iterable<Food> iterable = foodList;
            foodDao.saveAll(iterable);
            return foodList.size();
        }
    }

    @Override
    @Transactional
    public int subNumber(List<Food> foodList, int number) {
        if(foodList.size() == 0){
            return 0;
        }else {
            for (Food food: foodList ) {
                if(food.getFNumber() <= number){
                    food.setFNumber(0);
                }else{
                    food.setFNumber(food.getFNumber()-number);
                }
            }
            Iterable<Food> iterable = foodList;
            foodDao.saveAll(iterable);
            return foodList.size();
        }
    }

    @Override
    @Transactional
    public int addNumber(Map<String, Integer> cartMap) {
        List<Food> list = foodDao.findAllByFIdIn(new ArrayList<String>(cartMap.keySet()));
        if (list.size() != cartMap.size()){
            throw new SaleException(ExceptionStates.PRODUCT_NOT_EXIST);
        }
        for (Food food: list){
            food.setFNumber(food.getFNumber() + cartMap.get(food.getFId()));
        }
        List<Food> resultList = foodDao.saveAll(list);
        return resultList.size();
    }

    @Override
    public List<Food> findAllByCategoryId(String categoryId) {
        //TODO
        return null;
    }

    @Override
    public List<Food> findAllAvailableByCategoryId(String categoryId) {
        //TODO:
        return null;
    }

    @Override
    public List<Food> findAllUnavailableByCategoryId(String categoryId) {
        //TODO:
        return null;
    }

    @Override
    public List<Food> findAllByBoothId(String boothId) {
        return foodDao.findAllByBId(boothId);
    }

    @Override
    public Page<Food> findAllByBoothId(String boothId, Pageable pageable) {
        return foodDao.findAllByBId(boothId, pageable);
    }

    @Override
    public List<Food> findAllAvailableByBoothId(String boothId) {
        return foodDao.findAllByBIdAndFNumberGreaterThan(boothId, 0);
    }

    @Override
    public List<Food> findAllUnavailableByBoothId(String boothId) {
        return foodDao.findAllByBIdAndFNumberEquals(boothId, 0);
    }

    @Override
    public List<Food> findAllByCategoryIdAndBoothId(int categoryId, String boothId) {
        return foodDao.findAllByBIdAndCId(boothId, categoryId);
    }

    @Override
    public List<Food> findAllAvailableByCategoryIdAndBoothId(int categoryId, String boothId) {
        return foodDao.findAllByBIdAndCIdAndFNumberGreaterThan(boothId, categoryId, 0);
    }

    @Override
    public List<Food> findAllUnavailableByCategoryIdAndBoothId(int categoryId, String boothId) {
        return foodDao.findAllByBIdAndCIdAndFNumberEquals(boothId, categoryId, 0);
    }

    @Override
    public List<Food> findAllFoodById(List<String> foodIdList) {

        return foodDao.findAllByFIdIn(foodIdList);
    }

    @Override
    public List<Food> updateFood(List<Food> foodList) {
        //TODO: need to test
        return foodDao.saveAll(foodList);
    }

    @Override
    public Boolean updateFood(Food food) {
        foodDao.save(food);
        return true;
    }


}
