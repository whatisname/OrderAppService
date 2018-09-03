package com.xxy.ordersystem.controller.deliverer;

import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.deliverer
 * @date 9/2/2018 12:14 PM
 */
@Slf4j
@Controller
@RequestMapping("deliver/order")
public class DelivererOrderController {
    @Autowired
    private OrderService orderService;

    //抢单（快递员）
    @GetMapping("/startDeliver")
    public ResultVO<String> startDeliver(
            @RequestParam("opid") String opid,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String did = null;
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        Deliverer deliverer = null;
        //是否已登陆
        if (session != null) {
            deliverer = (Deliverer) session.getAttribute("deliverer");
            if (deliverer != null) {
                did = deliverer.getDId();
            } else {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        Boolean result = orderService.updateOrderTo_FOOD_DELIVERING(ordersPrimary, deliverer);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }

    //确认送到（快递员）
    @GetMapping("/confirmDelivered")
    public ResultVO<String> confirmDelivered(
            @RequestParam("opid") String opid,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String did = null;
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        //是否已登陆
        if (session != null) {
            Deliverer deliverer = (Deliverer) session.getAttribute("deliverer");
            if (deliverer != null) {
                did = deliverer.getDId();
            } else {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");
            }
        }
        //检查是否有权限
        if (!ordersPrimary.getDId().equals(did)) {
            log.info("{} - {}", getClass(), "没有权限操作订单(did:" + did + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(did:" + did + ", opid:" + opid + ")");
        }
        //检查订单状态（必须为送单中或用户已确认接单）
        Boolean result = orderService.updateOrderTo_FOOD_DELIVERED(ordersPrimary);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }

}
