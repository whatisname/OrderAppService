package com.xxy.ordersystem.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 8/14/2018 6:47 PM
 */
@Getter
public enum AccountState {
    ACTIVATE(0, "激活"),
    DEACTIVATE(1, "注销")
    ;

    private Integer code;
    private String message;

    AccountState(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
