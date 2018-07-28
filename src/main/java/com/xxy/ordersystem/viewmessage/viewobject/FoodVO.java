package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/12/2018 6:15 PM
 */
@Data
public class FoodVO {

//    private Timestamp fCreateTime;
//    private Timestamp fUpdateTime;
//    private String bId;
//    private Integer cId;

    @JsonProperty("id")
    private String fId;

    @JsonProperty("name")
    private String fName;

    @JsonProperty("imgurl")
    private String fImg;

    @JsonProperty("detail")
    private String fDescription;

    @JsonProperty(value = "number", defaultValue = "0") //defaultvalue 不起作用
    private Integer number = 0; // 用于前台购物车功能使用，默认为0，后台不赋值
//    private Integer fNumber;

    @JsonProperty("price")
    private BigDecimal fPrice;

//    private String fComment;


    @JsonProperty("booth")
    private BoothVO boothVO;

    @JsonProperty("state")
    private Integer state;

    @JsonProperty("boothName")
    private String boothName;
}
