package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/12/2018 6:31 PM
 */
@Data
public class CategoryVO {

//    private Timestamp cCreateTime;
//    private Timestamp cUpdateTime;
//    private String bId;

    @JsonProperty("name")
    private String cName;

    @JsonProperty("id")
    private String cId;

    @JsonProperty("foods")
    private List<FoodVO> foodList;
}
