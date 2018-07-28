package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Address;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 2:59 PM
 */
public interface AddressService {
    /**
     * 查找所有地址
     * @return {@link Address} list
     */
    List<Address> findAllAddress();

    /**
     * 查找该学生所有地址
     * @param studentId 学生id
     * @return {@link Address}
     */
    List<Address> findAllByStudentId(String studentId);

    /**
     * 查找学生默认地址
     * @param studentId 学生id
     * @return {@link Address}
     */
    Address findDefaultAddressByStudentId(String studentId);

    Address findAddressByAddressId(String addressId);

    Address addNewAddress(Address address);

//    /**
//     *
//     * @param address ！new {@link Address} 对象
//     * @return {@link Address}
//     */
//    Address addNewDefaultAddress(Address address);

    Address updateAddress(Address address);

    /**
     * 删除已存在的地址
     * @param address ！必须传入与数据库对应的Address，不可传入不存在的地址对象！！
     * @return {@link Address} 删除的地址
     */
    Address deleteExistingAddress(Address address);

    /**
     * 按照地址id删除地址。
     * ！删除时需要前台检查默认地址，默认地址不可删除，必须先设置新的默认地址再删除。
     * @param addrssId 地址id
     * @return  id存在：{@link Address} | id不存在：null
     */
    Address deleteAddressById(String addrssId);

    /**
     * 设为默认地址。
     * @param address ！必须传入与数据库对应的Address，不可传入不存在的地址对象！！
     * @return {@link Address} 设为默认的地址
     */
    Address saveDefault(Address address);
    Address saveDefault(String addrssId);
}
