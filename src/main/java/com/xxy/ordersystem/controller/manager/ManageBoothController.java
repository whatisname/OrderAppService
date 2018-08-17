package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.BoothForm;
import com.xxy.ordersystem.service.UpperService.intf.FileHandlerService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.KeyUtil;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.UploadFileResponse;
import com.xxy.ordersystem.viewmessage.converterUtil.PageToVOPage;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/12/2018 11:33 PM
 */
@RestController
@RequestMapping("/manage/booth")
@Slf4j
public class ManageBoothController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private BoothService boothService;
    @Autowired
    private FileHandlerService fileHandlerService;

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            Map<String, Object> map
    ) {
        PageRequest request = PageRequest.of(page, size);
        Page<Booth> boothPage = null;
        //查找商家
        if (state == -1) { // 所有店铺
            boothPage = boothService.findAllBooth(request);
        } else if (state == BoothStates.OPEN.getCode()) { //营业中店铺
            boothPage = boothService.findAllOpenBooth(request);
        } else if (state == BoothStates.REST.getCode()) { //休息中店铺
            boothPage = boothService.findAllRestBooth(request);
        } else if (state == BoothStates.CLOSE.getCode()) { //已关闭店铺
            boothPage = boothService.findAllCloseBooth(request);
        } else {
            log.error("{} - {}", getClass(), ExceptionStates.PARAM_ERROR);
            throw new SaleException(ExceptionStates.PARAM_ERROR);
        }
        //装配BoothVO
        Page<BoothVO> boothVOPage = PageToVOPage.fromBoothPageToVO(boothPage, request);

        map.put("boothVOPage", boothVOPage);
        return new ModelAndView("/manage/shop_list", map);
    }

    //传入booth id返回商店详情
    @GetMapping("/detail")
    public ModelAndView detail(
            @RequestParam("bid") String bid,
            Map<String, Object> map
    ) {
        BoothVO boothVO = new BoothVO();
        Booth booth = boothService.findBoothById(bid);
        if (booth == null) {
            log.error("{} - {}", getClass(), "bid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST);
        } else {
            BeanUtils.copyProperties(booth, boothVO);
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));
            boothVO.setState(BoothStates.stateOf(booth.getBState()));
            //查找所有food
            List<Food> foodList = foodService.findAllByBoothId(bid);
            List<FoodVO> foodVOList = new ArrayList<>();
            for (Food food : foodList) {
                FoodVO foodVO = new FoodVO();
                BeanUtils.copyProperties(food, foodVO);
                foodVOList.add(foodVO);
            }
            boothVO.setFoodVOList(foodVOList);
            map.put("boothVO", boothVO);
        }
        return new ModelAndView("/manage/shop_update", map);
    }

    @GetMapping("/delete")
    public ResultVO delete(
            @RequestParam("bid") String bid
    ) {
        Boolean result = boothService.disableBoothById(bid);
//        Boolean result = boothService.deleteBoothById(bid);
        if (result == true) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error("目标不存在或删除失败", null);
        }
    }

    @PostMapping("/add")
    public ModelAndView add(
            @Valid BoothForm boothForm,
            BindingResult bindingResult,
            Map<String, Object> map,
            @RequestParam("bState") Integer state,
            @RequestParam("bOwnerPassword") String password
    ) {
        String bid = KeyUtil.generateUniqueKeyId();
        Booth booth = new Booth();
        boothForm.setBId(bid);
        BeanUtils.copyProperties(boothForm, booth);
        booth.setBState(state);
        //defaultImg --> database
        booth.setBImg("/os/static/images/booth-default.png");
        booth.setBOwnerPassword(password);
        booth.setBOpenid("0");

        boothService.addBooth(booth);
        map.put("bid", bid);

        return new ModelAndView("redirect:/manage/booth/detail", map);
    }

    @GetMapping("/addReq")
    public ModelAndView addReq(
            Map<String, Object> map
    ) {
        return new ModelAndView("/manage/shop_add", map);
    }

    @PostMapping("/update")
    public ModelAndView update(
            Map<String, Object> map,
            @Valid BoothForm boothForm,
            BindingResult bindingResult
    ) {
        String bid = boothForm.getBId();
        Booth booth = boothService.findBoothById(bid);
        if (booth == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            return new ModelAndView("/error/404", map);
        }

        BeanUtils.copyProperties(boothForm, booth);
        Boolean result = boothService.updateBooth(booth);
        map.put("bid", bid);
        return new ModelAndView("redirect:/manage/booth/detail", map);
    }

    @PostMapping("/updatePassword")
    public ModelAndView updatePassword(
            Map<String, Object> map,
            @RequestParam("bOwnerPassword") String password,
            @RequestParam("bId") String bid
    ) {
        Booth booth = boothService.findBoothById(bid);
        if (booth == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            return new ModelAndView("/error/404", map);
        }

        booth.setBOwnerPassword(password);
        Boolean result = boothService.updateBooth(booth);
        map.put("bid", bid);
        return new ModelAndView("redirect:/manage/booth/detail", map);
    }

    @PostMapping("/updateImg")
    public ModelAndView updateImg(
            Map<String, Object> map,
            @RequestParam("bImg") String img,
            @RequestParam("bId") String bid,
            @RequestParam("file") MultipartFile file
    ) {
//        Booth booth = boothService.findBoothById(bid);
//        if (booth == null) {
//            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
//            return new ModelAndView("/error/404", map);
//        }

        //文件不能为空
        if (file.isEmpty()) {
            log.error("{} - {}", getClass(), "文件不能为空");
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "文件不能为空");
        }

        //判断大小
        if (file.getSize() > 1024*1024){
            log.error("{} - {}", getClass(), "文件大小超过2M");
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "文件大小超过2M");
        }

        UploadFileResponse response = fileHandlerService.saveBoothImg(file, bid);

//        booth.setBImg("/os/static/images/boothImg/"+response.getFileName()+"."+response.getFileExtension());
//        Boolean result = boothService.updateBooth(booth);
        map.put("bid", bid);
        return new ModelAndView("redirect:/manage/booth/detail", map);
    }

    @PostMapping("/updateState")
    public ModelAndView updateState(
            Map<String, Object> map,
            @RequestParam("bState") Integer state,
            @RequestParam("bId") String bid
    ) {
        Booth booth = boothService.findBoothById(bid);
        if (booth == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            return new ModelAndView("/error/404", map);
        }

        booth.setBState(state);
        Boolean result = boothService.updateBooth(booth);
        map.put("bid", bid);
        return new ModelAndView("redirect:/manage/booth/detail", map);
    }


}
