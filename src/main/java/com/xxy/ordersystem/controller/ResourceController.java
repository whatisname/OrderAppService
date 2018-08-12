package com.xxy.ordersystem.controller;

import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.FileTypes;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.FileHandlerService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.utils.QRUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.UploadFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;


/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/23/2018 8:15 PM
 */
@RestController
@RequestMapping("/res")
@Slf4j
public class ResourceController {
//    private String boothImgStoragePath = "D:\\fileUpload\\boothImg";

    private final ResourceLoader resourceLoader;
    @Autowired
    public ResourceController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private FileHandlerService fileHandlerService;

    @Autowired
    private BoothService boothService;

    /**
     * 上传商家图片
     * @param file
     * @param bid
     * @return
     */
    @PostMapping("/addBImg")
    public ResultVO<UploadFileResponse> addBImg(
            @RequestParam("file") MultipartFile file,
            @RequestParam("bid") String bid
            ){
        //文件不能为空
        if (file.isEmpty()) {
            log.error("{} - {}", getClass(), "文件不能为空");
            return MessageUtil.error("文件不能为空", null);
        }

        //判断大小
        if (file.getSize() > 1024*1024){
            log.error("{} - {}", getClass(), "文件大小超过2M");
            return MessageUtil.error("文件大小超过2M", null);
        }

        UploadFileResponse response = fileHandlerService.saveBoothImg(file, bid);

        return MessageUtil.successDefault(response);
    }

    /**
     * 上传商品图片
     * @param file
     * @param bid
     * @param fid
     * @return
     */
    @PostMapping("/addFImg")
    public ResultVO<UploadFileResponse> addFImg(
            @RequestParam("file") MultipartFile file,
            @RequestParam("bid") String bid,
            @RequestParam("fid") String fid
    ){
        //文件不能为空
        if (file.isEmpty()) {
            log.error("{} - {}", getClass(), "文件不能为空");
            return MessageUtil.error("文件不能为空", null);
        }

        //判断大小
        if (file.getSize() > 1024*1024){
            log.error("{} - {}", getClass(), "文件大小超过2M");
            return MessageUtil.error("文件大小超过2M", null);
        }

        UploadFileResponse response = fileHandlerService.saveFoodImg(file, bid, fid);

        return MessageUtil.successDefault(response);
    }

    /**
     * 上传广告图片
     * @param file
     * @return
     */
    @PostMapping("/addAdvImg")
    public ResultVO<UploadFileResponse> addAdvImg(
            @RequestParam("file") MultipartFile file
    ){
        //文件不能为空
        if (file.isEmpty()) {
            log.error("{} - {}", getClass(), "文件不能为空");
            return MessageUtil.error("文件不能为空", null);
        }

        //判断大小
        if (file.getSize() > 1024*1024){
            log.error("{} - {}", getClass(), "文件大小超过2M");
            return MessageUtil.error("文件大小超过2M", null);
        }

        UploadFileResponse response = fileHandlerService.saveAdvImg(file, "ADid");

        return MessageUtil.successDefault(response);
    }

    /**
     * 获取图片
     * 显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
     * @param filename
     * @return
     */
//    @GetMapping("/img/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<?> getFile(
//            @PathVariable String filename
//    ) {
//        try {
//            filename = filename.replaceAll("-", "/");
//            String fileType = FilenameUtils.getExtension(filename);
//            if (fileType.equals(FileTypes.PNG.getType())){
//                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resourceLoader.getResource("file:" + Paths.get(fileHandlerService.BoothImgStoragePath, filename).toString()));
//            }else if (
//                    fileType.equals(FileTypes.JPEG.getType()) ||
//                    fileType.equals(FileTypes.JPG.getType())
//                    ){
//                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resourceLoader.getResource("file:" + Paths.get(fileHandlerService.BoothImgStoragePath, filename).toString()));
//            }else {
//                log.error("{} - {}",getClass(), ExceptionStates.WRONG_FILE_TYPE.getMessage(), "不支持的图片类型");
//                throw new SaleException(ExceptionStates.WRONG_FILE_TYPE);
//
////                return ResponseEntity.ok().contentType(MediaType.ALL).body(resourceLoader.getResource("file:" + Paths.get(BoothImgStoragePath, filename).toString()));
//            }
////            response.addHeader("Content-Disposition", "attachment;filename=image.png";
////            这样写在浏览器上打开链接会直接下载图片，放入image标签的src属性会显示图片
//
////            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(BoothImgStoragePath, filename).toString()));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }


    /**
     * 获取二维码
     * @return
     */
    @GetMapping("/getQR")
    @ResponseBody
    public ResponseEntity<?> getQR(){
        String QRName = QRUtil.generateQR("www.baidu.com", 200, 200);
        if (QRName.equals("")){
            return ResponseEntity.status(500).build();
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("title", QRName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .headers(headers)
                    .body(resourceLoader.getResource("file:" + Paths.get(QRUtil.QRDir, QRName).toString()));
//            response.addHeader("Content-Disposition", "attachment;filename=image.png";
//            这样写在浏览器上打开链接会直接下载图片，放入image标签的src属性会显示图片
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 获取广告
     * @param filename
     * @return
     */
//    @GetMapping("/adv/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<?> getAdv(
//            @PathVariable String filename
//    ){
//        try {
//            filename = filename.replaceAll("-", "/");
//            String fileType = FilenameUtils.getExtension(filename);
//            if (fileType.equals(FileTypes.PNG.getType())){
//                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resourceLoader.getResource("file:" + Paths.get(fileHandlerService.AdvImgStoragePath, filename).toString()));
//            }else if (
//                    fileType.equals(FileTypes.JPEG.getType()) ||
//                            fileType.equals(FileTypes.JPG.getType())
//                    ){
//                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resourceLoader.getResource("file:" + Paths.get(fileHandlerService.AdvImgStoragePath, filename).toString()));
//            }else {
//                log.error("{} - {}",getClass(), ExceptionStates.WRONG_FILE_TYPE.getMessage(), "不支持的图片类型");
//                throw new SaleException(ExceptionStates.WRONG_FILE_TYPE);
//            }
////            response.addHeader("Content-Disposition", "attachment;filename=image.png";
////            这样写在浏览器上打开链接会直接下载图片，放入image标签的src属性会显示图片
//
////            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(BoothImgStoragePath, filename).toString()));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
