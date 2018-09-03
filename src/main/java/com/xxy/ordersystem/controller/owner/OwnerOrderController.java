package com.xxy.ordersystem.controller.owner;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 卖家端订单管理
 *
 * @author X
 * @package com.xxy.ordersystem.controller.owner
 * @date 8/8/2018 9:55 PM
 */
@RestController
@RequestMapping("/owner/order")
@Slf4j
public class OwnerOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/listAll")
    public ModelAndView listAll(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam("bid") String bid,
            Map<String, Object> map
    ) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrdersByBoothId(bid, false, request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("boolVal", true);
        map.put("date", new Timestamp(System.currentTimeMillis()));
        return new ModelAndView("/list", map);
    }


    /**
     * 接收订单（商家）
     * orderstate == paied
     *
     * @param opid
     * @return
     */
    @PostMapping("/startPrepare")
    public ResultVO<String> startPrepare(
            @RequestParam("opid") String opid,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String bid = null;
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        if (session != null) {
            Booth booth = (Booth) session.getAttribute("booth");
            if (booth != null) {
                // 检查是否具有权限
                bid = booth.getBId();
                if (!bid.equals(ordersPrimary.getBId())) {
                    log.info("{} - {}", getClass(), "非法权限：无权限操作订单");
                }
            }
        }
        //检查是否有权限
        if (!ordersPrimary.getBId().equals(bid)) {
            log.info("{} - {}", getClass(), "没有权限操作订单(bid:" + bid + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(bid:" + bid + ", opid:" + opid + ")");
        }
        //检查订单状态（必须为已支付状态）
        if (ordersPrimary.getOPState().equals(OrderStates.ORDER_PAID)) {
            Boolean result = orderService.updateOrderTo_PREPARING_FOOD(ordersPrimary);
            if (result) {
                return MessageUtil.success();
            } else {
                return MessageUtil.error();
            }
        } else {
            log.info("{} - {}", getClass(), "订单状态不正确，opid：" + ordersPrimary.getOPId() + ", 订单可能未支付。");
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE.getCode(), "订单状态不正确，opid：" + ordersPrimary.getOPId() + ", 订单可能未支付。");
        }
    }

    //准备完成（商家）
    @PostMapping("/finishPrepare")
    public ResultVO<String> finishPrepare(
            @RequestParam("opid") String opid,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        String bid = null;
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        if (session != null) {
            Booth booth = (Booth) session.getAttribute("booth");
            if (booth != null) {
                // 检查是否具有权限
                bid = booth.getBId();
                if (!bid.equals(ordersPrimary.getBId())) {
                    log.info("{} - {}", getClass(), "非法权限：无权限操作订单");
                }
            }
        }
        //检查是否有权限
        if (!ordersPrimary.getBId().equals(bid)) {
            log.info("{} - {}", getClass(), "没有权限操作订单(bid:" + bid + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(bid:" + bid + ", opid:" + opid + ")");
        }
        Boolean result = orderService.updateOrderTo_READY_TO_DELIVER(ordersPrimary);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }

    /**
     * 取消订单（商家）
     * 必须为未支付状态
     *
     * @param request
     * @param opid
     * @param reason
     * @return
     */
    @GetMapping("/cancel")
    public ResultVO cancel(
            HttpServletRequest request,
            @RequestParam("opid") String opid,
            @RequestParam(value = "reason", defaultValue = "取消订单") String reason
    ) {
        HttpSession session = request.getSession();
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        Booth booth = null;
        //是否已登陆
        if (session != null) {
            booth = (Booth) session.getAttribute("booth");
            if (booth == null) {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        //检查是否有权限
        if (!ordersPrimary.getSId().equals(booth.getBId())) {
            log.info("{} - {}", getClass(), "没有权限操作订单(bid:" + booth.getBId() + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(bid:" + booth.getBId() + ", opid:" + opid + ")");
        }
        Boolean result = orderService.updateOrderTo_CANCELED_BY_SHOP(ordersPrimary, reason);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }

}
