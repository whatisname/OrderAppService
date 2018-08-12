package com.xxy.ordersystem.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * @author X
 * @package com.xxy.ordersystem.form
 * @date 8/11/2018 2:13 AM
 */
@Data
public class BoothForm {

    private String bId;

    @NotEmpty(message = "必须填写店名")
    private String bName;
    @NotEmpty(message = "必须填写餐厅")
    private String bCanteen;
    @NotEmpty(message = "必须填写窗口号")
    private String bWindow;
//    @NotEmpty(message = "必须选择区域")
    private Integer bQuyu;

//    private String bImg;

//    @NotEmpty(message = "必须选择状态")
//    private Integer bState;

    @NotEmpty(message = "必须填写负责人")
    private String bOwnerName;
    @NotEmpty(message = "必须填写手机号")
    private String bOwnerPhone;
    @NotEmpty(message = "必须填写邮箱")
    private String bOwnerEmail;

//    private String bImg;

//    @NotEmpty(message = "必须选择状态")
//    private Integer bState;

//    private String bOwnerPassword;

//    private String bOpenid;

    private String bComment;
//
//    private Timestamp bCreateTime;
//    private Timestamp bUpdateTime;
}
