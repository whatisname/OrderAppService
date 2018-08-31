package com.xxy.ordersystem.controller.user;

import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.user
 * @date 8/15/2018 7:06 PM
 */
@RestController
@RequestMapping("/user/deliver")
@Slf4j
public class UserDelivererController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/tracking")
    public ResultVO tracking(
            @RequestParam("opid") String opid
    ){

        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null){
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        //TODO load order order delivery status
        return MessageUtil.success();
    }
}
