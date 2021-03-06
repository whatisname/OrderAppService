package com.xxy.ordersystem.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/12/2018 11:55 PM
 */
@Getter
public enum BoothStates {

    OPEN(0, "营业中"),
    REST(1, "休息中"),
    CLOSE(2, "已关闭");


    private int code;
    private String state;

    BoothStates(Integer code, String state) {
        this.code = code;
        this.state = state;
    }

    public static String stateOf(Integer code){
        switch (code){
            case 0:
                return OPEN.getState();
            case 1:
                return REST.getState();
            case 2:
                return CLOSE.getState();
            default:
                return "其他状态";
        }

    }
}
