package com.xxy.ordersystem.controller.owner;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.BoothForm;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.user
 * @date 8/15/2018 7:03 PM
 */
@RestController
@RequestMapping("/owner/booth")
@Slf4j
public class OwnerBoothController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private BoothService boothService;

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

    @PostMapping("/update")
    public ResultVO update(
            @Valid BoothForm boothForm,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            log.error("{} - {}", this.getClass(), ExceptionStates.PARAM_ERROR.getMessage());
            return MessageUtil.error(bindingResult.getFieldError().getDefaultMessage(), null);
        }
        Booth booth = boothService.findBoothById(boothForm.getBId());
        String old_password = booth.getBOwnerPassword();
        BeanUtils.copyProperties(boothForm, booth);
        Boolean result = boothService.updateBooth(booth);

        if (result == false){
            log.error("{} - {}", this.getClass(), "店铺更新失败");
            return MessageUtil.error("店铺更新失败", null);
        }
        log.info("Booth (bid:{}) 已更新。", boothForm.getBId());
        return MessageUtil.success();
    }

    /**
     *
     * deactivate shop
     * @param bid
     * @return
     */
    @GetMapping("/disable")
    public ResultVO disbale(
            @RequestParam("bid") String bid
    ){
        Booth booth = boothService.findBoothById(bid);
        if (booth == null){
            log.error("{} - {}", getClass(), "bid不存在");
            return MessageUtil.error("bid不存在", null);
        }
        boothService.disableBooth(booth);
        return MessageUtil.success();
    }

}
