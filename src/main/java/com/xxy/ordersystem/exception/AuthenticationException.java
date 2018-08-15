package com.xxy.ordersystem.exception;

import com.xxy.ordersystem.enums.ExceptionStates;

/**
 * @author X
 * @package com.xxy.ordersystem.exception
 * @date 8/15/2018 11:13 AM
 */
public class AuthenticationException extends RuntimeException{
    private Integer code;

    public AuthenticationException(ExceptionStates exceptionStates) {
        super(exceptionStates.getMessage());
        this.code = exceptionStates.getCode();
    }

    public AuthenticationException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
