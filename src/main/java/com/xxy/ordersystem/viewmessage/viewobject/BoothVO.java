package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/12/2018 11:39 PM
 */
@Data
public class BoothVO {

    @JsonProperty("id")
    private String bId;

    @JsonProperty("name")
    private String bName;

    @JsonProperty("canteen")
    private String bCanteen;
    @JsonProperty("window")
    private String bWindow;
    @JsonProperty("quyucode")
    private int bQuyu;
    @JsonProperty("quyu")
    private String quyu;
    @JsonProperty("imgurl")
    private String bImg;
    @JsonProperty("statecode")
    private Integer bState;
    @JsonProperty("state")
    private String state;
//
    private String bOwnerName;
    private String bOwnerPhone;

    private String bOwnerEmail;
    private String bOwnerPassword;
//    private String bOpenid;

    @JsonProperty("comment")
    private String bComment;
    private Timestamp bCreateTime;
    private Timestamp bUpdateTime;

    @JsonProperty("foods")
    private List<FoodVO> foodVOList;
}
