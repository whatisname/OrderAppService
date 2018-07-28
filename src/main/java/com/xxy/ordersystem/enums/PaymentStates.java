package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/18/2018 5:24 PM
 */
@Getter
public enum PaymentStates {
    FAIL(0, "支付失败"),
    SUCCESS(1, "支付成功"),
    PENDING(2, "待支付");

    private Integer code;
    private String message;

    PaymentStates(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
