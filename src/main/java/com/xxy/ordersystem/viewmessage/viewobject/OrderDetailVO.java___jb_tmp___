package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject.metaViewObject
 * @date 7/14/2018 12:42 PM
 */
@Data
public class OrderDetailVO {
    //    private String oDId;
    @JsonProperty("number")
    private int oDNumber;
    @JsonProperty("price")
    private BigDecimal oDPrice;
    @JsonProperty("sum")
    private BigDecimal oDSum;


//    private String fId;
//    private String oPId;

    @JsonProperty("food")
    private FoodVO foodVO;
}
