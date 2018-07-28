package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/13/2018 11:14 PM
 */
@Data
public class SearchResultVO {
    @JsonProperty("booths")
    private List<BoothVO> boothVOList;
    @JsonProperty("foods")
    private List<FoodVO> foodVOList;
    @JsonProperty("historyFoods")
    private List<FoodVO> historyFoodVOList;
}
