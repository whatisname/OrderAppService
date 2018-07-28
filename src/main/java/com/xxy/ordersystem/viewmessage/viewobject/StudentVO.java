package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject
 * @date 7/14/2018 12:51 PM
 */
@Data
public class StudentVO {
    @JsonProperty("id")
    private String sId;
    @JsonProperty("name")
    private String sName;
    @JsonProperty("gender")
    private Byte sGender;
    @JsonProperty("phone")
    private String sPhone;
    @JsonProperty("xibiebanji")
    private String sXibieBanji;
    @JsonProperty("idCard")
    private String sIdcard;
    @JsonProperty("studentId")
    private String sStudentid;
    @JsonProperty("email")
    private String sEmail;
    @JsonProperty("quyu")
    private int sQuyu;
    //    private String sPassword;
    @JsonProperty("openId")
    private String sOpenid;
//    private String sComment;
//    private Timestamp sCreateTime;
//    private Timestamp sUpdateTime;


}
