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
    @JsonProperty("quyuCode")
    private int dQuyu;

    @JsonProperty("quyu")
    private String quyu;

    //    private String dPassword;

    @JsonProperty("comment")
    private String dComment;

    @JsonProperty("createTime")
    private Timestamp dCreateTime;

    @JsonProperty("updateTime")
    private Timestamp dUpdateTime;

    @JsonProperty("accountState")
    private Integer dAccountState;

    private Integer numberM; //月送单量
    private Integer numberW; //周送单量
    private Integer numberD; //日送单量
    private Integer numberAll; //总送单量
}
