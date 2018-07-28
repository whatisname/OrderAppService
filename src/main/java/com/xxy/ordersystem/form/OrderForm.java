package com.xxy.ordersystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author X
 * @package com.xxy.ordersystem.form
 * @date 7/18/2018 10:51 PM
 */
@Data
public class OrderForm {
    @NotEmpty(message = "必须登陆或填入学生id")
    private String sid;

    @NotEmpty(message = "必须选择地址")
    private String aid;

    @NotEmpty(message = "必须选择商品")
    private String items;

//    @NotEmpty(message = "必须登陆微信")
//    private String openid;

}
