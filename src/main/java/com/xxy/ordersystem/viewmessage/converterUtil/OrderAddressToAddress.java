package com.xxy.ordersystem.viewmessage.converterUtil;

import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.converterUtil
 * @date 8/17/2018 3:36 AM
 */
public class OrderAddressToAddress {

    public static Address orderAddressToAddress(OrdersPrimary ordersPrimary){
//        ordersPrimary.setOPAddress(
//                address.getAName()
//                        .concat("#").concat(address.getAPhone())
//                        .concat("#").concat(String.valueOf(address.getAQuyu()))
//                        .concat("#").concat(address.getAAddress())
//                        .concat("#").concat(address.getADormintory())
//        );
        Address address = new Address();
        String add = ordersPrimary.getOPAddress();
        String[] addArray = add.split("#");
        if (addArray.length >= 5){
            address.setAName(addArray[0]);
            address.setAPhone(addArray[1]);
            address.setAQuyu(Integer.valueOf(addArray[2]));
            address.setAAddress(addArray[3]);
            address.setADormintory(addArray[4]);
            address.setAComment(ordersPrimary.getOPComment());
        }else{
            throw new SaleException(ExceptionStates.CONVERT_FAIL.getCode(), "无法转换为Address（OrdersPrimary.oPAddress包含的#少于4个）");
        }
        return address;
    }
}
