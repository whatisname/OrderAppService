package com.xxy.ordersystem.viewmessage.viewobject;

import lombok.Data;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/19/2018 12:37 AM
 */
@Data
public class CartVO {
    private String id;
    private Integer number;

    public CartVO(String id, Integer number) {
        this.id = id;
        this.number = number;
    }
}
