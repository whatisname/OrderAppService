package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/12/2018 12:05 AM
 */

@Getter
public enum OrderStates {

    ORDER_GENERATED(1, "订单已下达", "o_order_date"),
    PREPARING_FOOD(2, "外卖准备中", "o_confirm_date"),
    READY_TO_DELIVER(3, "可抢单", "o_prepare_finish_date"),
    FOOD_DELIVERING(4, "配送中", "o_start_deliver_date"),
    FOOD_DELIVERED(5, "外卖员已送达", "o_deliver_arrive_date"),
    USER_RECEIVED(6, "用户已接单", "o_finish_date"),
    REFUNDING(7, "退款申请中(用户确认订单以后请求退款或投诉退款)", ""),
    ERROR_STATE(8, "错误状态", ""),
    CANCELED_BY_SHOP(9, "被商家取消（无法准备或无法配送）", ""),
    CANCELED_BY_USER(10, "被用户取消（用户确认收到之前取消，如无法收到订单，快递员送未送达等）", ""),
    ORDER_PAID(11, "订单已支付", "o_p_payment_date");


    private int code;
    private String message;
    private String column;

    OrderStates(int code, String message, String column) {
        this.code = code;
        this.message = message;
        this.column = column;
    }

    public static String stateOf(Integer code) {
        switch (code) {
            case 1:
                return ORDER_GENERATED.getMessage();
            case 2:
                return PREPARING_FOOD.getMessage();
            case 3:
                return READY_TO_DELIVER.getMessage();
            case 4:
                return FOOD_DELIVERING.getMessage();
            case 5:
                return FOOD_DELIVERED.getMessage();
            case 6:
                return USER_RECEIVED.getMessage();
            case 7:
                return REFUNDING.getMessage();
            case 8:
                return ERROR_STATE.getMessage();
            case 9:
                return CANCELED_BY_SHOP.getMessage();
            case 10:
                return CANCELED_BY_USER.getMessage();
            case 11:
                return ORDER_PAID.getMessage();
            default:
                return "illegal state";
        }
    }
}
