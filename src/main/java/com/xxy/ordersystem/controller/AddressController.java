package com.xxy.ordersystem.controller;

import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.AddressForm;
import com.xxy.ordersystem.service.intf.AddressService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.AddressVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/26/2018 11:34 PM
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO<List<AddressVO>> getAllAddress(
            @RequestParam("sid") String sid
    ){
        List<Address> addressList = addressService.findAllByStudentId(sid);

        List<AddressVO> addressVOList = new ArrayList<>();
        for (Address address:addressList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            addressVO.setQuyu(Quyu.areaOf(address.getAQuyu()));
            if(address.getADefault() == true){
                addressVO.setIsSelected(true);
            }
            addressVOList.add(addressVO);
        }

        return MessageUtil.successDefault(addressVOList);
    }


    @PostMapping("/add")
    public ResultVO add(
            @Valid AddressForm addressForm,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            log.error("{} - {}", this.getClass(), ExceptionStates.PARAM_ERROR.getMessage());
            return MessageUtil.error(bindingResult.getFieldError().getDefaultMessage(), null);
        }
        Address address = new Address();
        BeanUtils.copyProperties(addressForm, address);
        Address result = addressService.addNewAddress(address);
        if (result == null){
            log.error("{} - {}", this.getClass(), "地址创建失败");
            return MessageUtil.error("地址创建失败", null);
        }
        return MessageUtil.success();
    }

    @PostMapping("/update")
    public ResultVO update(
            @Valid AddressForm addressForm,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            log.error("{} - {}", this.getClass(), ExceptionStates.PARAM_ERROR.getMessage());
            return MessageUtil.error(bindingResult.getFieldError().getDefaultMessage(), null);
        }
        Address address = addressService.findAddressByAddressId(addressForm.getAId());
        BeanUtils.copyProperties(addressForm, address);
        Address result = addressService.updateAddress(address);
        if (result == null){
            log.error("{} - {}", this.getClass(), "地址更新失败");
            return MessageUtil.error("地址更新失败", null);
        }
        return MessageUtil.success();
    }

    @GetMapping("/delete")
    public ResultVO  delete(
            @RequestParam("sid") String sid,
            @RequestParam("aid") String aid
    ){
        Address address = addressService.findAddressByAddressId(aid);
        if (address == null){
            log.error("{} - {}", getClass(), "找不到aid");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "找不到aid");
        }
        if (!address.getSId().equals(sid)){
            log.error("{} - {}", getClass(), "非法操作");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "非法操作");
        }
        if (address.getADefault() == true){
            log.error("{} - {}", getClass(), "默认地址不可删除！");
            return MessageUtil.error("默认地址不可删除!", null);
        }
        Address result = addressService.deleteAddressById(aid);
        if (result != null){
            return MessageUtil.success();
        }else {
            log.error("{} - {}", getClass(), "操作失败");
            return MessageUtil.error("操作失败", null);
        }
    }

    @GetMapping("/setDefault")
    public ResultVO setDefault(
            @RequestParam("sid") String sid,
            @RequestParam("aid") String aid
    ){
        Address address = addressService.findAddressByAddressId(aid);
        if (address == null){
            log.error("{} - {}", getClass(), "aid不存在");
            return MessageUtil.error("aid不存在", null);
        }
        Address result = addressService.saveDefault(address);
        if (result == null){
            log.error("{} - {}", getClass(), "更新失败");
            return MessageUtil.error("更新失败", null);
        }
        return MessageUtil.success();
    }
}
