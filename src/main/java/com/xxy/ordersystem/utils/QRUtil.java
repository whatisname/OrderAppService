package com.xxy.ordersystem.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.FileTypes;
import com.xxy.ordersystem.exception.SaleException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/24/2018 5:37 PM
 */
public class QRUtil {

    public static final String QRDir = "upload-dir/QRImg";

    /**
     *  生成二维码图片
     * @param url 要生成二维码的路径
     * @param width 二维码宽度
     * @param height 二维码高度
     */
    public static String generateQR(String url, int width, int height) {
        if (url != null && !"".equals(url)) {
            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                String QRname =  FileNameUtil.generateFileName();
                Path savePath = Paths.get(QRDir, QRname+"."+FileTypes.PNG.getType());
                MatrixToImageWriter.writeToPath(m, "png", savePath);
                return QRname+"."+FileTypes.PNG.getType();
            } catch (IOException|WriterException e) {
                e.printStackTrace();
            }
        }else{
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "url不能为空");
        }
        return "";
    }
}
