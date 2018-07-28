package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.AddressDao;
import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.service.intf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/13/2018 3:06 PM
 */
@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> findAllAddress() {

        return addressDao.findAll();
    }

    @Override
    public List<Address> findAllByStudentId(String studentId) {

        return addressDao.findAllBySId(studentId);
    }

    @Override
    public Address findDefaultAddressByStudentId(String studentId) {

        return addressDao.findAddressBySIdAndADefaultEquals(studentId, true);
    }

    @Override
    public Address findAddressByAddressId(String addressId) {
        return addressDao.findAddressByAId(addressId);
    }

    @Override
    public Address addNewAddress(Address address) {
        //检查是设为默认地址
        if (address.getADefault()) { //添加新默认地址
            return this.addNewDefaultAddress(address);
        }else{
            return addressDao.save(address);
        }
    }

    //    @Override
    private Address addNewDefaultAddress(Address address) {
        //检查default是否为true
        if (!address.getADefault()){
            address.setADefault(true);
        }

        Address defaultAddress = this.findDefaultAddressByStudentId(address.getSId());
        //有默认地址
        if(defaultAddress != null){
            defaultAddress.setADefault(false);
            Iterable<Address> iterable = Arrays.asList(defaultAddress, address);
            addressDao.saveAll(iterable);
            return address;
        }else{ //无默认地址
            addressDao.save(address);
            return address;
        }

    }

    @Override
    public Address updateAddress(Address address) {
        if (!address.getADefault()) {
            return addressDao.save(address);
        }else{ //设为新默认地址
            this.saveDefault(address);
            return address;
        }
    }

    @Override
    public Address deleteExistingAddress(Address address) {
        addressDao.delete(address);
        return address;
    }

    @Override
    public Address deleteAddressById(String addrssId) {
        Address address = this.findAddressByAddressId(addrssId);
        if (address != null){
            return this.deleteExistingAddress(address);
        }else {
            return null;
        }
    }

    @Override
    public Address saveDefault(Address address) {
        //取消默认地址
        Address addressDefault = this.findDefaultAddressByStudentId(address.getSId());
        //如果存在默认地址
        if(addressDefault != null){
            addressDefault.setADefault(false);
            address.setADefault(true);
            Iterable<Address> iterable = Arrays.asList(address,addressDefault);
            addressDao.saveAll(iterable);
            return address;
        }else{
            address.setADefault(true);
            return addressDao.save(address);
        }
    }

    @Override
    public Address saveDefault(String addrssId) {

        return this.saveDefault( this.findAddressByAddressId(addrssId));
    }
}
