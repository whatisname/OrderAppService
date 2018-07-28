package com.xxy.ordersystem.service.UpperService.intf;

import com.xxy.ordersystem.viewmessage.UploadFileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.intf
 * @date 7/24/2018 2:50 AM
 */
public interface FileHandlerService {
    public static final String BoothImgStoragePath = "upload-dir/boothImg";
    public static final String FoodImgFolder = "foodImg";
    public static final String AdvImgStoragePath = "upload-dir/advImg";

    //保存
    UploadFileResponse saveBoothImg(MultipartFile file, String bid);
    UploadFileResponse saveFoodImg(MultipartFile file, String bid, String fid);
    UploadFileResponse saveAdvImg(MultipartFile file, String ADid);

    //删除
    Boolean deleteAdv(String advId);

    //获取
    List<String> getAllAdv();


}
