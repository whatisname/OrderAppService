package com.xxy.ordersystem.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author X
 * @package com.xxy.ordersystem.form
 * @date 7/28/2018 2:50 AM
 */
@Data
public class AddressForm {

    @NotEmpty(message = "必须填写id")
    private String aId;
    @NotEmpty(message = "必须填写区域")
    private int aQuyu;
    @NotEmpty(message = "必须填写宿舍")
    private String aDormintory;
    @NotEmpty(message = "必须填写地址")
    private String aAddress;
    @NotEmpty(message = "必须选择是否默认")
    private Boolean aDefault;

    private String aComment;

    @NotEmpty(message = "必须登陆或填入学生id")
    private String sid;


}
