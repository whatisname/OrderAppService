package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/14/2018 12:53 PM
 */
@Data
public class AddressVO {
    @JsonProperty("id")
    private String aId;

    @JsonProperty("quyucode")
    private int aQuyu;
    @JsonProperty("quyu")
    private String quyu;

    @JsonProperty("dorm")
    private String aDormintory;

    @JsonProperty("detail")
    private String aAddress;

    @JsonProperty("isDefault")
    private Boolean aDefault;

    @JsonProperty("comment")
    private String aComment;

//    private Timestamp aCreateTime;
//    private Timestamp aUpdateTime;
//    private String sId;


    @JsonProperty("isSelected")
    private Boolean isSelected = false;
}
