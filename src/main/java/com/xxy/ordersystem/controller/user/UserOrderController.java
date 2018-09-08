package com.xxy.ordersystem.controller.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.entity.Student;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.OrderForm;
import com.xxy.ordersystem.service.UpperService.imp.WebSocket;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.service.intf.AddressService;
import com.xxy.ordersystem.service.intf.OrderPrimaryService;
import com.xxy.ordersystem.service.intf.StudentService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.viewobject.CartVO;
import com.xxy.ordersystem.viewmessage.viewobject.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.user
 * @date 8/15/2018 7:10 PM
 */
@RestController
@RequestMapping("/user/order")
@Slf4j
public class UserOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderPrimaryService orderPrimaryService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private WebSocket webSocket;

    /**
     * 获取一个订单的所有细节
     *
     * @param sid
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> getOrdersById(@RequestParam("sid") String sid,
                                            @RequestParam("id") String id) {
//        OrderDTO orderDTO = orderService.findOrderByPrimaryId("1531724848751343090");
        OrderDTO orderDTO = orderService.findOrderByPrimaryId(id);
        if (orderDTO.getOPId() == sid) {
            return MessageUtil.successDefault(orderDTO);
        } else {
            log.error("{} - {}", getClass(), ExceptionStates.UNAUTHORIZED_ACCESS.getMessage());
            throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS);
        }
    }

    /**
     * 获取所有订单（忽略细节）
     *
     * @param sid
     * @param page
     * @param size
     * @param state
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderVO>> getAllOrders(
            @RequestParam("sid") @NotEmpty(message = "sid不能为空") String sid,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1") Integer state
    ) {
        // TODO 验证sid
        if (sid == null) {
            log.error("{} - {}", getClass(), ExceptionStates.PARAM_ERROR);
            throw new SaleException(ExceptionStates.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = null;
        if (state == -1) {
            orderDTOPage = orderService.findAllOrdersByStudentId(sid, false, request);
        } else {
            orderDTOPage = orderService.findAllOrdersByStudentIdAndState(sid, state, false, request);
        }
//        if (orderDTOList.size() == 0){
//            log.error("{} - {}", getClass(), ExceptionStates.NO_RESULT.getMessage());
//            throw new SaleException(ExceptionStates.NO_RESULT);
//        }
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderDTOList = orderDTOPage.getContent();

        return MessageUtil.successDefault(orderDTOList);
    }

    /**
     * 创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<String> create(
            HttpServletRequest request,
            @Valid OrderForm orderForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.error("{} - {}", this.getClass(), ExceptionStates.PARAM_ERROR.getMessage());
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
//            return MessageUtil.error(bindingResult.getFieldError().getDefaultMessage(), null);
        }
//        是否已登陆
        HttpSession session = request.getSession();
        Student student = null;
//        if (session != null) {
//            student = (Student) session.getAttribute("student");
//            if (student == null) {
//                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");
//            }
//        }

        student = studentService.findStudentByStudentId(orderForm.getSid());
        List<CartVO> cartVOList = new ArrayList<>();
        try {
            Gson gson = new Gson();
            cartVOList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<CartVO>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("{} - {}", this.getClass(), e.getStackTrace().toString());
            log.error("{} - {} - {}", this.getClass(), "items参数JSON转换错误",
                    ExceptionStates.PARAM_ERROR.getMessage());
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "items参数JSON转换错误");
//            return MessageUtil.error("items参数JSON转换错误", null);
        }
        if (cartVOList.size() == 0) {
            log.error("{} - {}", this.getClass(), ExceptionStates.CART_EMPTY.getMessage());
            throw new SaleException(ExceptionStates.CART_EMPTY);
//            return MessageUtil.error(ExceptionStates.CART_EMPTY.getMessage(), null);
        }

        Map<String, Integer> cartMap = cartVOList.stream().collect(Collectors.toMap(CartVO::getId, CartVO::getNumber));

        String orderParimaryId = orderService.addNewOrder(student.getSId(), orderForm.getAid(), cartMap);
        if (orderParimaryId == null) {
            return MessageUtil.error("添加操作失败", null);
        }
        return MessageUtil.successDefault(orderParimaryId);
    }

    /**
     * 取消订单（学生）
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
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        //是否已登陆
        HttpSession session = request.getSession();
        Student student = null;
        if (session != null) {
            student = (Student) session.getAttribute("student");
            if (student == null) {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        //检查是否有权限
        if (!ordersPrimary.getSId().equals(student.getSId())) {
            log.info("{} - {}", getClass(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
        }
        //检查订单状态（必须为未支付）
        if (ordersPrimary.getOPState().equals(OrderStates.ORDER_GENERATED)) {
            Boolean result = orderService.updateOrderTo_CANCELED_BY_USER(ordersPrimary, reason);
            if (result) {
                return MessageUtil.success();
            } else {
                return MessageUtil.error();
            }
        } else {
            log.info("{} - {}", getClass(), "订单状态不正确，opid：" + ordersPrimary.getOPId());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE.getCode(), "订单状态不正确，opid：" + ordersPrimary.getOPId());
        }
    }

    /**
     * 支付订单
     */
    //TODO
    @GetMapping("/paymentConfirm")
    public ResultVO paymentConfirm(
            @RequestParam("opid") String opid
    ) {
        OrdersPrimary ordersPrimary = orderPrimaryService.findOrdersPrimaryById(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }

        //更新订单状态
        orderService.updateOrderTo_ORDER_PAID(ordersPrimary);
        // 通知商家
        webSocket.sendMessage("有新的订单");
        // 通知快递员
        // TODO
        return MessageUtil.success();
    }


    //确认收到（用户）
    @GetMapping("/confirmReceived")
    public ResultVO<String> confirmReceived(
            HttpServletRequest request,
            @RequestParam("opid") String opid
    ) {
        HttpSession session = request.getSession();
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        Student student = null;
        //是否已登陆
        if (session != null) {
            student = (Student) session.getAttribute("student");
            if (student == null) {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        //检查是否有权限
        if (!ordersPrimary.getSId().equals(student.getSId())) {
            log.info("{} - {}", getClass(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
        }
        //检查订单状态（必须为快递员确认送达或派送中）
        if (ordersPrimary.getOPState().equals(OrderStates.FOOD_DELIVERED)
                || ordersPrimary.getOPState().equals(OrderStates.FOOD_DELIVERING)) {
            Boolean result = orderService.updateOrderTo_USER_RECEIVED(ordersPrimary);
            if (result) {
                return MessageUtil.success();
            } else {
                return MessageUtil.error();
            }
        } else {
            log.info("{} - {}", getClass(), "订单状态不正确，opid：" + ordersPrimary.getOPId());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE.getCode(), "订单状态不正确，opid：" + ordersPrimary.getOPId());
        }
    }

    //更改地址
    @GetMapping("/changeAddress")
    public ResultVO changeAddress(
            HttpServletRequest request,
            @RequestParam("opid") String opid,
            @RequestParam("aid") String aid
    ) {
        HttpSession session = request.getSession();
        OrdersPrimary ordersPrimary = orderService.findOrderByPrimaryId(opid);
        if (ordersPrimary == null) {
            log.info("{} - {}", getClass(), "opid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "opid不存在");
        }
        Address address = addressService.findAddressByAddressId(aid);
        if (address == null) {
            log.info("{} - {}", getClass(), "aid不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST.getCode(), "aid不存在");
        }
        Student student = null;
        //是否已登陆
        if (session != null) {
            student = (Student) session.getAttribute("student");
            if (student == null) {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        //检查是否有权限
        if (!ordersPrimary.getSId().equals(student.getSId())) {
            log.info("{} - {}", getClass(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
        }
        Boolean result = orderService.updateOrderAddress(ordersPrimary, address);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }

    //订单退款
    @GetMapping("/applyRefund")
    public ResultVO applyRefund(
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
        Student student = null;
        //是否已登陆
        if (session != null) {
            student = (Student) session.getAttribute("student");
            if (student == null) {
                throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "未登录");

            }
        }
        //检查是否有权限
        if (!ordersPrimary.getSId().equals(student.getSId())) {
            log.info("{} - {}", getClass(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
            throw new SaleException(ExceptionStates.UNAUTHORIZED_OPERATION.getCode(), "没有权限操作订单(sid:" + student.getSId() + ", opid:" + opid + ")");
        }
        Boolean result = orderService.updateOrderTo_REFUNDING(ordersPrimary, reason);
        if (result) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error();
        }
    }
}
