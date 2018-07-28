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
public class DelivererVO {
    @JsonProperty("id")
    private String dId;
    @JsonProperty("name")
    private String dName;
    @JsonProperty("phone")
    private String dPhone;
    @JsonProperty("xibiebanji")
    private String dXibieBanji;
    @JsonProperty("idcard")
    private String dIdcard;
    @JsonProperty("email")
    private String dEmail;
    @JsonProperty("quyu")
    private int dQuyu;
    //    private String dPassword;
//    private String dComment;
    @JsonProperty("createTime")
    private Timestamp dCreateTime;
//    private Timestamp dUpdateTime;
}
