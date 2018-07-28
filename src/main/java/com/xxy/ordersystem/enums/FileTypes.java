package com.xxy.ordersystem.enums;

import lombok.Getter;

/**
 * @author X
 * @package com.xxy.ordersystem.enums
 * @date 7/23/2018 10:16 PM
 */
@Getter
public enum FileTypes {
    JPG(1, "jpg"),
    PNG(2, "png"),
    JPEG(3, "jpeg")
    ;


    private Integer code;
    private String type;

    FileTypes(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public static Boolean contains(String type){
        if (
                type.equals(JPG.type) ||
                        type.equals(JPEG.type) ||
                        type.equals(PNG.type)
                ){
            return true;
        }else {
            return false;
        }
    }
}
