package com.xxy.ordersystem.exception;

import com.xxy.ordersystem.enums.ExceptionStates;

/**
 * @author X
 * @package com.xxy.ordersystem.exception
 * @date 7/16/2018 1:45 AM
 */
public class SaleException extends RuntimeException{
    private Integer code;

    public SaleException(ExceptionStates exceptionStates) {
        super(exceptionStates.getMessage());
        this.code = exceptionStates.getCode();
    }

    public SaleException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
