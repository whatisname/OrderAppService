package com.xxy.ordersystem.exception;

import com.xxy.ordersystem.enums.ExceptionStates;
import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.exception
 * @date 8/15/2018 11:34 AM
 */
@Getter
public class WebSaleException extends RuntimeException{

    private Integer code;

    public WebSaleException(ExceptionStates exceptionStates) {
        super(exceptionStates.getMessage());
        this.code = exceptionStates.getCode();
    }

    public WebSaleException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
