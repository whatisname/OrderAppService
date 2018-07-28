package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/16/2018 1:47 AM
 */
@Getter
public enum ExceptionStates {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    OUT_OF_STOCK(2, "没有库存或库存不足"),
    NO_SUCH_DELIVERER(3, "快递员id不存在"),
    NO_SUCH_ORDERPRIMRY(4, "订单主表id不存在"),
    NO_SUCH_ORDERDETAIL(5, "订单副表id不存在"),
    CANCEL_FAIL_ERROR_STATE(6, "订单已配送，无法取消"),
    ERROR_ORDER_STATE(7, "订单状态不正确"),
    PAYMENT_FAIL(8, "支付失败"),
    ORDER_CREATE_FAIL(9, "订单创建失败"),
    NO_RESULT(10, "没有查询结果"),
    NOSUCH_STUDENT(11, "学生id不存在"),
    ID_NOT_EXIST(12, "id不存在"),

    PARAM_ERROR(20, "参数不正确"),
    CART_EMPTY(21, "（没有商品）购物车为空"),


    UNAUTHORIZED_ACCESS(100, "授权失败或没有权限查询"),

    WRONG_FILE_TYPE(200, "非法文件类型"),

    UPDATE_FAIL(400, "更新数据失败"),
    CREATE_FAIL(401, "新增数据失败"),
    DELETE_FAIL(402, "删除数据失败"),


    ;

    private Integer code;
    private String message;

    ExceptionStates(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
