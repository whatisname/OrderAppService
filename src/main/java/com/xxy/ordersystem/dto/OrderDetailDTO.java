package com.xxy.ordersystem.dto;

import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.entity.OrdersDetail;
import lombok.Data;

/**
 * @author X
 * @package com.xxy.ordersystem.dto
 * @date 7/16/2018 6:00 PM
 */
@Data
public class OrderDetailDTO extends OrdersDetail {
    private Food food;
}
