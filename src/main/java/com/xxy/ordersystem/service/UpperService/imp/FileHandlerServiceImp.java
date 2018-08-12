package com.xxy.ordersystem.service.UpperService.imp;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.FileTypes;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.FileHandlerService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.FileNameUtil;
import com.xxy.ordersystem.viewmessage.UploadFileResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.imp
 * @date 7/24/2018 2:50 AM
 */
@Service
@Slf4j
public class FileHandlerServiceImp implements FileHandlerService {

    @Autowired
    private BoothService boothService;
    @Autowired
    private FoodService foodService;

    @Override
    public UploadFileResponse saveBoothImg(MultipartFile file, String bid) {

        //查找booth
        Booth booth = boothService.findBoothById(bid);
        if (booth == null){
            log.error("{} - {}",getClass(), "bid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "bid不存在");
        }

        //提取文件类型（后缀）
        String fileType = this.getFileExtention(file);

        //设置存储路径
        String imageName = FileNameUtil.generateFileName();
        Path uploadPath = Paths.get(ImgBaseStoragePath, BoothImgFolder, imageName+"."+fileType);

        //存储文件
        UploadFileResponse response = this.saveFile(file, uploadPath);
        response.setFileDownloadUri("/os/static/images/"+BoothImgFolder+"/"+imageName+"."+fileType);

        //更新booth imgurl
        String old_imgurl = booth.getBImg();

        booth.setBImg("/os/static/images/"+BoothImgFolder+"/"+imageName+"."+fileType);
        Boolean result = boothService.updateBooth(booth);
        if (result == false){
            log.error("{} - {}",getClass(), "更新失败");
            throw new SaleException(ExceptionStates.UPDATE_FAIL.getCode(), "更新失败");
        }else{
            this.deleteFile(old_imgurl);
        }

        return response;
    }

    @Override
    public UploadFileResponse saveFoodImg(MultipartFile file, String bid, String fid) {

        //查找booth
        Booth booth = boothService.findBoothById(bid);
        if (booth == null){
            log.error("{} - {}",getClass(), "bid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "bid不存在");
        }
        //查找food
        Food food = foodService.findByFoodId(fid);
        if (food == null){
            log.error("{} - {}",getClass(), "fid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "fid不存在");
        }

        //提取文件类型（后缀）
        String fileType = this.getFileExtention(file);

        //设置存储路径
        String imageName = FileNameUtil.generateFileName();
        Path uploadPath = Paths.get(ImgBaseStoragePath,FoodImgFolder, imageName+"."+fileType);

        //存储文件
        UploadFileResponse response = this.saveFile(file, uploadPath);
        response.setFileDownloadUri("/os/static/images/"+FoodImgFolder+"/"+imageName+"."+fileType);

        //更新food imgurl
        String old_imgurl = food.getFImg();

        food.setFImg("/os/static/images/"+FoodImgFolder+"/"+imageName+"."+fileType);
        Boolean result = foodService.updateFood(food);
        if (result == false){
            log.error("{} - {}",getClass(), "更新失败");
            throw new SaleException(ExceptionStates.UPDATE_FAIL.getCode(), "更新失败");
        }else{
            this.deleteFile(old_imgurl);
        }

        return response;
    }

    @Override
    public UploadFileResponse saveAdvImg(MultipartFile file, String ADid) {
        //提取文件类型（后缀）
        String fileType = this.getFileExtention(file);

        //设置存储路径
        String imageName = FileNameUtil.generateFileName();
        Path uploadPath = Paths.get(ImgBaseStoragePath,AdvImgFolder, imageName+"."+fileType);

        //存储文件
        UploadFileResponse response = this.saveFile(file, uploadPath);
        response.setFileDownloadUri("/os/static/images/"+AdvImgFolder+"/"+imageName+"."+fileType);

        //TODO: 更新ADs address
        //TODO: delete old adv img

        return response;
    }

    @Override
    public Boolean deleteAdv(String advId) {

        //TODO:
        return null;
    }

    @Override
    public List<String> getAllAdv() {
        return null;
    }


    //================内部方法


    private String getFileExtention(MultipartFile file){
        String fileType = "";
        if (FilenameUtils.getExtension(file.getOriginalFilename()).equals("")
                && FilenameUtils.getExtension(file.getName()).equals("")){
            fileType = "jpg";
        }else if (!FilenameUtils.getExtension(file.getOriginalFilename()).equals("")){
            fileType = FilenameUtils.getExtension(file.getOriginalFilename());
        }else if(!FilenameUtils.getExtension(file.getName()).equals("")){
            fileType = FilenameUtils.getExtension(file.getName());
        }
        if(!FileTypes.contains(fileType)){
            log.error("{} - {}",getClass(), "文件类型错误（只允许上传jpg，png和jpeg图片格式）");
            throw new SaleException(ExceptionStates.WRONG_FILE_TYPE.getCode(), "文件类型错误（只允许上传jpg，png和jpeg图片格式）");
        }else {
            return fileType;
        }
    }


    //删除旧图片
    private Boolean deleteFile(String old_imgurl){
//        os/static/images/booth-default.png
        old_imgurl = old_imgurl.replaceAll("/os", "src/main/resources");
        Path oldImgPath = Paths.get(old_imgurl);
        //旧图片
        File oldImg = new File(oldImgPath.toString());
        if (oldImg.exists()) {
            oldImg.delete();
            return true;
        }else {
            return false;
        }
    }

    private UploadFileResponse saveFile(MultipartFile file, Path uploadPath){

        UploadFileResponse response = new UploadFileResponse();

        //创建新文件
        File file1 = new File(uploadPath.toString());
        log.info("{} - {}", getClass(), file1.getAbsolutePath());
        log.info("{}", file1.getParentFile().getPath());

        //判断文件路径是否存在
        try {
            if (!file1.exists()){ //file1不存在
                if (!file1.getParentFile().exists()){ //文件夹不存在
                    File file2 = new File(file1.getParent());
                    file2.mkdirs();
                    //保存图片
                    Files.copy(file.getInputStream(), uploadPath);
                }else {
                    //保存图片
                    Files.copy(file.getInputStream(), uploadPath);
                }
            }else{ //图片已存在
                file1.deleteOnExit();
                //TODO 测试（生成的文件名不会重复）
                //覆盖图片
                Files.copy(file.getInputStream(), uploadPath);

            }
            response.setFileName(FilenameUtils.getName(file1.getName()));
            response.setFileFullName(file1.getName());
            response.setFileType(file.getContentType());
            response.setFileExtension(FilenameUtils.getExtension(file1.getName()));
            response.setFileDownloadUri("/os/static/images/");
            response.setSize(file.getSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
