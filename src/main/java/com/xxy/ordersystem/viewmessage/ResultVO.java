package com.xxy.ordersystem.viewmessage;

import lombok.Data;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage
 * @date 7/12/2018 5:40 PM
 */
@Data
public class ResultVO<T> {

    /** 错误码.*/
    private int code;

    /** 提示信息. */
    private String message;

    private T data;
}
