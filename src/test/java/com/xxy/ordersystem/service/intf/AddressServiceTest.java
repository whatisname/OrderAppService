package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 3:09 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void findAllAddress() {
        List<Address> list = addressService.findAllAddress();
        log.info("size: {}", list.size());
    }

    @Test
    public void findAllByStudentId() {
        List<Address> list = addressService.findAllByStudentId("1");
        log.info("size: {}", list.size());

    }

    @Test
    public void findDefaultAddressByStudentId() {
        Address address = addressService.findDefaultAddressByStudentId("1");
        log.info("size: {}", address.toString());
    }

    @Test
    public void addNewAddress() {
//        Address address = new Address(KeyUtil.generateUniqueKeyId(), Quyu.EAST.getCode(), "宿舍", "东院仁园三号4343", true, null, "10");
//        Address result = addressService.addNewAddress(address);
//        log.info("保存：{}", result);
//        Assert.assertEquals(address, result);
    }

    @Test
    public void updateAddress() {
        Address address = addressService.findAddressByAddressId("2");
        address.setADormintory("aaa");
        Address result = addressService.updateAddress(address);
        log.info("更新为：{}", result);
        Assert.assertEquals(address, result);
    }

    @Test
    public void deleteExistingAddress() {
        Address address = addressService.findAddressByAddressId("5");
        Address result = addressService.deleteExistingAddress(address);
        log.info("删除：{}", result);
    }

    @Test
    public void deleteAddressById() {
        Address result = addressService.deleteAddressById("5");
        log.info("删除：{}", result);
    }

    @Test
    public void saveDefault() {
//    Address saveDefault(Address address);
    }

    @Test
    public void saveDefault2() {
        Address address = addressService.saveDefault("2");
        log.info("设为默认地址：{}", address);

    }

    @Test
    public void addNewDefaultAddress() {
        //Address addNewDefaultAddress(Address address);
    }
}