package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/19/2018 10:07 PM
 */
@Getter
public enum SearchType {

    ALL(0, "搜索全部", "all"),
    BOOTH(1, "搜索店铺", "booth"),
    FOOD(2, "搜索商品", "food")
    ;


    private Integer code;
    private String message;
    private String type;

    SearchType(Integer code, String message, String type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }
}
