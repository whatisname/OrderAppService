package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.service.UpperService.imp.WebSocket;
import com.xxy.ordersystem.utils.KeyUtil;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 9/7/2018 9:17 PM
 */
@RestController
@RequestMapping("/manage/message")
public class ManageMessageController {
    @Autowired
    private WebSocket webSocket;

    @GetMapping("/sendSocket")
    public ResultVO sendSocket(
            @RequestParam("message") String message
    ) {
//        webSocket.sendMessage(message);
        webSocket.sendMessage(KeyUtil.generateUniqueKeyId());
        return MessageUtil.success();
    }

}
