package com.xxy.ordersystem.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xalan.internal.lib.ExsltBase;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.OrderForm;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/14/2018 12:19 PM
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StudentService studentService;

    /**
     * 获取一个订单的所有细节
     * @param sid
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> getOrdersById(@RequestParam("sid")String sid,
                                            @RequestParam("id")String id){
//        OrderDTO orderDTO = orderService.findOrderByPrimaryId("1531724848751343090");
        OrderDTO orderDTO = orderService.findOrderByPrimaryId(id);
        if (orderDTO.getOPId() == sid) {
            return MessageUtil.successDefault(orderDTO);
        }else{
            log.error("{} - {}", getClass(), ExceptionStates.UNAUTHORIZED_ACCESS.getMessage());
            throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS);
        }
    }

    /**
     * 获取所有订单（忽略细节）
     * @param sid
     * @param page
     * @param size
     * @param state
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderVO>> getAllOrders(@RequestParam("sid") @NotEmpty(message = "sid不能为空") String sid,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                                          @RequestParam(value = "state", defaultValue = "-1")Integer state){
        // TODO 验证sid
        if(sid == null){
            log.error("{} - {}", getClass(), ExceptionStates.PARAM_ERROR);
            throw new SaleException(ExceptionStates.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = null;
        if (state == -1) {
            orderDTOPage = orderService.findAllOrdersByStudentId(sid, false, request);
        }else{
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
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO<String> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("{} - {}", this.getClass(), ExceptionStates.PARAM_ERROR.getMessage());
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
//            return MessageUtil.error(bindingResult.getFieldError().getDefaultMessage(), null);
        }
        List<CartVO> cartVOList = new ArrayList<>();
        try{
            Gson gson = new Gson();
            cartVOList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<CartVO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            log.error("{} - {}",this.getClass(), e.getStackTrace().toString());
            log.error("{} - {} - {}", this.getClass(), "items参数JSON转换错误",
                    ExceptionStates.PARAM_ERROR.getMessage());
            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "items参数JSON转换错误");
//            return MessageUtil.error("items参数JSON转换错误", null);
        }
        if (cartVOList.size() == 0){
            log.error("{} - {}", this.getClass(), ExceptionStates.CART_EMPTY.getMessage());
            throw new SaleException(ExceptionStates.CART_EMPTY);
//            return MessageUtil.error(ExceptionStates.CART_EMPTY.getMessage(), null);
        }

        Map<String, Integer> cartMap = cartVOList.stream().collect(Collectors.toMap(CartVO::getId, CartVO::getNumber));

        String orderParimaryId = orderService.addNewOrder(orderForm.getSid(), orderForm.getAid(), cartMap);
        if (orderParimaryId == null){
            return MessageUtil.error("添加操作失败", null);
        }
        return MessageUtil.successDefault(orderParimaryId);
    }

    /**
     * 取消订单
     * @param sid
     * @param id
     * @param reason
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO<String> cancel(@RequestParam("sid")String sid,
                                   @RequestParam("id")String id,
                                   @RequestParam(value = "reason", defaultValue = "取消订单") String reason){
        //TODO 身份验证

        if (orderService.validateStudentAndOrder(sid, id)){
            log.error("{} - {}", getClass(), ExceptionStates.UNAUTHORIZED_ACCESS);
            throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS);
        }
        Boolean result = orderService.updateOrderTo_CANCELED_BY_USER(id, reason);
        if (result == true) {
            return MessageUtil.successDefault(id);
        } else{
            return MessageUtil.error();
        }
    }

    //支付订单
    //TODO

    //接收订单（商家）
    @PostMapping("/startPrepare")
    public ResultVO<String> startPrepare(
            @RequestParam("bid") String bid,
            @RequestParam("id") String id
    ){
        Boolean result = orderService.updateOrderTo_PREPARING_FOOD(id);
        if (result){
            return MessageUtil.successDefault(id);
        }else{
            return MessageUtil.error();
        }
    }

    //准备完成（商家）
    @PostMapping("/finishPrepare")
    public ResultVO<String> finishPrepare(
            @RequestParam("bid") String bid,
            @RequestParam("id") String id
    ){
        Boolean result = orderService.updateOrderTo_READY_TO_DELIVER(id);
        if (result){
            return MessageUtil.successDefault(id);
        }else{
            return MessageUtil.error();
        }
    }

    //抢单（快递员）
    @PostMapping("/startDeliver")
    public ResultVO<String> startDeliver(
            @RequestParam("id") String id,
            @RequestParam("did") String did
    ){
        Boolean result = orderService.updateOrderTo_FOOD_DELIVERING(id, did);
        if (result){
            return MessageUtil.successDefault(id);
        }else{
            return MessageUtil.error();
        }
    }

    //确认送到（快递员）
    @PostMapping("/confirmDelivered")
    public ResultVO<String> confirmDelivered(
            @RequestParam("id") String id,
            @RequestParam("did") String did
    ){
        Boolean result = orderService.updateOrderTo_FOOD_DELIVERED(id);
        if (result){
            return MessageUtil.successDefault(id);
        }else{
            return MessageUtil.error();
        }
    }

    //确认收到（用户）
    @PostMapping("/confirmReceived")
    public ResultVO<String> confirmReceived(
            @RequestParam("id") String id,
            @RequestParam("sid") String sid
    ){
        Boolean result = orderService.updateOrderTo_USER_RECEIVED(id);
        if (result){
            return MessageUtil.successDefault(id);
        }else{
            return MessageUtil.error();
        }
    }

}
