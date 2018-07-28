package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/13/2018 1:36 AM
 */
@Getter
public enum Quyu {

    EAST(1, "东区"), WEST(2, "西区"),DEFAULT(0, "默认");

    private Integer code;
    private String area;

    Quyu(Integer code, String area) {
        this.code = code;
        this.area = area;
    }

    public static String areaOf(Integer code){
//        return quyu.getArea();
        switch (code){
            case 1:
                return EAST.getArea();
            case 2:
                return WEST.getArea();
            case 0:
                return DEFAULT.getArea();
            default:
                return "Unknow area";
        }
    }
}
