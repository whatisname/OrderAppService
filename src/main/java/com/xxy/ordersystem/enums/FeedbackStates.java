package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 9/3/2018 7:47 AM
 */
@Getter
public enum FeedbackStates {
    PENDING(1, "待解决"),
    SOLVED(0, "已解决"),
    IGNORED(2, "已忽略")
    ;

    private Integer code;
    private String message;

    FeedbackStates(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Boolean hasCode(Integer code){
        if (code != PENDING.getCode()
                && code != SOLVED.getCode()
                && code != IGNORED.getCode()
                ){
            return false;
        }else {
            return true;
        }
    }
}
