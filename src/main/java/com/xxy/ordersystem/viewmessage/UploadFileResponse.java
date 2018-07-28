package com.xxy.ordersystem.viewmessage;

import lombok.Data;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/23/2018 7:03 PM
 */
@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private String fileExtension;
    private long size;

    public UploadFileResponse() {
    }
}
