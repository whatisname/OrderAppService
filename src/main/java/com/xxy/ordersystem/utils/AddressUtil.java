package com.xxy.ordersystem.utils;

import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 9/2/2018 7:55 PM
 */
public class AddressUtil {
    public static String toAddressString(Address address) {
        return address.getAName()
                .concat("#").concat(address.getAPhone())
                .concat("#").concat(String.valueOf(address.getAQuyu()))
                .concat("#").concat(address.getAAddress())
                .concat("#").concat(address.getADormintory())
                .concat(("#").concat(address.getAComment()));
    }

    public static Address toAddress(String addressString){
        Address address = new Address();
        String[] addArray = addressString.split("#");
        if (addArray.length >= 6){
            address.setAName(addArray[0]);
            address.setAPhone(addArray[1]);
            address.setAQuyu(Integer.valueOf(addArray[2]));
            address.setAAddress(addArray[3]);
            address.setADormintory(addArray[4]);
            address.setAComment(addArray[5]);
        }else{
            throw new SaleException(ExceptionStates.CONVERT_FAIL.getCode(), "无法转换为Address（OrdersPrimary.oPAddress包含的#少于6个）");
        }
        return address;
    }
}
