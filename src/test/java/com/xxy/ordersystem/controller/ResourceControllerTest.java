package com.xxy.ordersystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/23/2018 8:19 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ResourceControllerTest {
    @Autowired
    private ResourceController resourceController;

    @Test
    public void upload() {
//        try {
//            File file = new File("D:\\1532295538.png");
//            FileInputStream fileInputStream = new FileInputStream(file);
//            byte[] filebyte = new byte[fileInputStream.available()];
//            fileInputStream.read(filebyte);
////            log.info("{}", filebyte);
//            MultipartFile file1 = new MockMultipartFile(file.getName(), filebyte);
//            resourceController.upload(file1, "a");
//            fileInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}