package com.xxy.ordersystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

/**
 * @author X
 * @package com.xxy.ordersystem.form
 * @date 8/14/2018 6:58 PM
 */
@Data
public class DelivererForm {

    private String dId;
    @NotEmpty(message = "必须填写名字")
    private String dName;
    @NotEmpty(message = "必须填写手机号")
    private String dPhone;
    @NotEmpty(message = "必须填写系别班级")
    private String dXibieBanji;
    @NotEmpty(message = "必须填写身份证")
    private String dIdcard;
    @NotEmpty(message = "必须填写邮箱")
    private String dEmail;

    private Integer dQuyu;
//    @NotEmpty(message = "必须填写名字")
//    private String dPassword;
    private String dComment;
//    private Timestamp dCreateTime;
//    private Timestamp dUpdateTime;

//    private Integer dAccountState;
}
