package com.xxy.ordersystem.utils;

import com.xxy.ordersystem.viewmessage.ResultVO;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/12/2018 8:20 PM
 */
public class MessageUtil {
    private final static Integer ERROR = 0;
    private final static Integer SUCCESS = 1;
    private final static Integer DEFAULT = 2;

    /**
     * 内置装填Result方法
     * @param code 状态码
     * @param message 消息
     * @param object {@link ResultVO}.data 对象
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    private static ResultVO fillResult(Integer code, String message, Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 自定义错误消息和 {@link ResultVO}.data 对象。
     * @param message 消息
     * @param object {@link ResultVO}.data 对象
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    public static ResultVO error(String message, Object object){

        return fillResult(ERROR, message, object);
    }

    /**
     * 自定义 {@link ResultVO}.data 对象，默认错误消息"error"。
     * @param object {@link ResultVO}.data 对象
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    public static ResultVO errorDefault(Object object){

        return error("error", object);
    }

    /**
     * 返回一个错误消息 {@link ResultVO}。默认错误消息"error"，无数据对象。
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    public static ResultVO error(){

        return errorDefault(null);
    }

    /**
     * 自定义成功消息和 {@link ResultVO}.data 对象。
     * @param message 消息
     * @param object {@link ResultVO}.data 对象
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    public static ResultVO success(String message, Object object){

        return fillResult(SUCCESS, message, object);
    }

    /**
     * 自定义 {@link ResultVO}.data 对象，默认成功消息"success"。
     * @param object  {@link ResultVO}.data 对象
     * @return 返回view通信消息对象 {@link ResultVO}。
     */
    public static ResultVO successDefault(Object object){

        return success("success", object);
    }

    /**
     * 返回一个从成功消息 {@link ResultVO}。默认成功消息"success"，无数据对象。
     * @return 返回view通信消息对象 {@link ResultVO}
     */
    public static ResultVO success(){

        return successDefault(null);
    }

    //=============================================================
    public static String messageFilter(String message){
        //TODO:
        return message;
    }

}
