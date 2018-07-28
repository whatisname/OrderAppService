package com.xxy.ordersystem.service.UpperService.imp;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.dto.OrderDetailDTO;
import com.xxy.ordersystem.entity.*;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.service.intf.*;
import com.xxy.ordersystem.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.imp
 * @date 7/14/2018 12:38 PM
 */
@Service
@Slf4j
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderPrimaryService orderPrimaryService;
    @Autowired
    private OrdersDetailService ordersDetailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DelivererService delivererService;
    @Autowired
    private BoothService boothService;
    @Autowired
    private FoodService foodService;

    @Override
    public OrderDTO findOrderByPrimaryId(String orderPrimaryId) {
        OrderDTO orderDTO = new OrderDTO();
        //get primary order
        OrdersPrimary ordersPrimary = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("({}) - {}", this.getClass() ,ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }else{
            BeanUtils.copyProperties(ordersPrimary, orderDTO);

            //get detail orders
            List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(orderPrimaryId);
            if (ordersDetailList.size() == 0){
                log.error("({}) - {}", this.getClass() ,ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
                throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
            }
            //load student
            Student student = studentService.findStudentByStudentId(ordersPrimary.getSId());
            orderDTO.setStudent(student);

            //load address
            Address address = addressService.findAddressByAddressId(ordersPrimary.getAId());
            orderDTO.setAddress(address);

            //load deliever

            if (ordersPrimary.getDId() != null){
                if (!ordersPrimary.getDId().equals("")){
                    Deliverer deliverer = delivererService.findDelivererByDId(ordersPrimary.getDId());
                    orderDTO.setDeliverer(deliverer);
                }
            }

            //load foods
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            for(OrdersDetail ordersDetail: ordersDetailList){
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

                //load OrderDetailDTO
                BeanUtils.copyProperties(ordersDetail, orderDetailDTO);
                //load Food
                Food food = foodService.findByFoodId(ordersDetail.getFId());
                orderDetailDTO.setFood(food);
                orderDetailDTOList.add(orderDetailDTO);
            }
            orderDTO.setOrderDetailDTOList(orderDetailDTOList);

            //load booth
            Booth booth = boothService.findBoothById(ordersPrimary.getBId());
            orderDTO.setBooth(booth);

            return orderDTO;
        }
    }

    @Override
    public List<OrderDTO> findAllOrders() {
//        orderPrimaryService.findall
        //TODO:
        return null;
    }

    @Override
    public Page<OrderDTO> findAllOrdersByStudentId(String studentId, Boolean haveDetail, Pageable pageable) {
        Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByStudentId(studentId, pageable);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        if (!haveDetail) {
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        }else{
            //TODO
            return null;
        }
    }

    @Override
    public Page<OrderDTO> findAllOrdersByStudentIdAndState(String studentId, Integer orderState, Boolean haveDetail, Pageable pageable) {
        Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByStudentIdAndOrderState(studentId, orderState, pageable);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        if (!haveDetail) {
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        }else{
            //TODO
            return null;
        }

    }

    private Page<OrderDTO> convertToPageOrderDTO(Page<OrdersPrimary> ordersPrimaryPage, Pageable pageable){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrdersPrimary ordersPrimary: ordersPrimaryPage.getContent()){
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(ordersPrimary, orderDTO);
            orderDTOList.add(orderDTO);
        }
        return new PageImpl<OrderDTO>(orderDTOList, pageable, ordersPrimaryPage.getTotalElements());
    }

    @Override
    public List<OrdersPrimary> findAllOrdersByBoothId(String boothId, Boolean haveDetail, Pageable pageable) {
//        Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByBoothId(boothId, pageable);
//        List<OrderDTO> orderDTOList = new ArrayList<>();

//        if (!haveDetail) {
//            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
//        }else{
        //TODO
        return null;
//        }
    }

    @Override
    public Page<OrdersPrimary> findAllOrdersByBoothIdAndState(String boothId, Integer orderState, Boolean haveDetail, Pageable pageable) {
        //TODO
        return null;
    }

    @Override
    public Page<OrderDTO> findAllOrdersByDelivererId(String delivererId, Boolean haveDetail, Pageable pageable) {
        //TODO
        return null;
    }

    @Override
    public Page<OrderDTO> findAllOrdersByDelivererIdAndState(String delivererId, Integer orderState, Boolean haveDetail, Pageable pageable) {
        //TODO
        return null;
    }


    @Override
    @Transactional
    public String addNewOrder(String studentId, String addressId, Map<String, Integer> orderDetailMap) {
        if (studentService.findStudentByStudentId(studentId) == null){
            log.error("{} - {}", getClass(), ExceptionStates.NOSUCH_STUDENT.getMessage());
            throw new SaleException(ExceptionStates.NOSUCH_STUDENT);
        }
        OrdersPrimary ordersPrimary = new OrdersPrimary();
        ordersPrimary.setOPId(KeyUtil.generateUniqueKeyId());
        ordersPrimary.setSId(studentId);
        ordersPrimary.setAId(addressId);
        ordersPrimary.setOPState(OrderStates.ORDER_GENERATED.getCode());

        //1. 查询商品（数量价格）
        List<String> foodIdList = new ArrayList<>( orderDetailMap.keySet());
        List<Food> foodList = foodService.findAllFoodById(foodIdList);
        if (foodList == null) {
            log.error("({}) - {}", this.getClass() ,ExceptionStates.PRODUCT_NOT_EXIST.getMessage());
            throw new SaleException(ExceptionStates.PRODUCT_NOT_EXIST);
        }else{
            if (foodList.size() == 0) {
                log.error("({}) - {}", this.getClass() ,ExceptionStates.PRODUCT_NOT_EXIST.getMessage());
                throw new SaleException(ExceptionStates.PRODUCT_NOT_EXIST);
            }
        }

        // set booth id
        ordersPrimary.setBId(foodList.get(0).getBId());



        //2. 计算价格, load ordersDetailVOList
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        List<OrdersDetail> ordersDetailList = new ArrayList<>();

        for(Food food: foodList){
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setODId(KeyUtil.generateUniqueKeyId());

            // 获取数量
            int amount = orderDetailMap.get(food.getFId());
            ordersDetail.setODPrice(food.getFPrice());
            ordersDetail.setODSum(food.getFPrice().multiply(new BigDecimal(amount)));
            ordersDetail.setFId(food.getFId());
            ordersDetail.setOPId(ordersPrimary.getOPId());
            ordersDetail.setODNumber(amount);

            //减库存(->5.)
            if (food.getFNumber() >= amount) {
                food.setFNumber(food.getFNumber() - amount);
            }else {
                log.error("({}) - {}", this.getClass() ,ExceptionStates.OUT_OF_STOCK.getMessage());
                throw new SaleException(ExceptionStates.OUT_OF_STOCK);
            }


            //求总价格
            sum = food.getFPrice().multiply(new BigDecimal(amount)).add(sum);

            //add to ordersDetailList
            ordersDetailList.add(ordersDetail);

        }
        //set sum
        ordersPrimary.setOPSum(sum);

        //3.计算快递费
        ordersPrimary.setOPDeliverFee(new BigDecimal(1));

        //4. 写入数据库
        OrdersPrimary opResult = orderPrimaryService.saveNewOrdersPrimary(ordersPrimary);
        List<OrdersDetail> odResult = ordersDetailService.saveAllOrderDetails(ordersDetailList);
        if (opResult == null || odResult == null){
            log.error("({}) - {}", this.getClass() ,ExceptionStates.ORDER_CREATE_FAIL.getMessage());
            throw new SaleException(ExceptionStates.ORDER_CREATE_FAIL);
        }else{
            if (odResult.size() == 0){
                log.error("({}) - {}", this.getClass() ,ExceptionStates.ORDER_CREATE_FAIL.getMessage());
                throw new SaleException(ExceptionStates.ORDER_CREATE_FAIL);
            }
        }

        //5. 减库存(save food list)
        foodService.updateFood(foodList);

        return ordersPrimary.getOPId();
    }

    @Override
    public Boolean updateOrderTo_CANCELED_BY_USER(String orderPrimaryId, String comment) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        //1. 判断订单状态
        if (!ordersPrimary.getOPState().equals(OrderStates.ORDER_GENERATED.getCode())
                && !ordersPrimary.getOPState().equals(OrderStates.PREPARING_FOOD.getCode())
                && !ordersPrimary.getOPState().equals(OrderStates.READY_TO_DELIVER.getCode())
                ){
            log.error("({}) - {}", this.getClass() ,ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage());
            throw new SaleException(ExceptionStates.CANCEL_FAIL_ERROR_STATE);
        }
        //2. 修改订单状态，填写退单理由
        ordersPrimary.setOPState(OrderStates.CANCELED_BY_USER.getCode());
        ordersPrimary.setOPComment(comment);
        orderPrimaryService.updateOrder(ordersPrimary);
        //3. 返回库存
        //get detail orders
        List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(orderPrimaryId);
        if (ordersDetailList.size() == 0){
            log.error("({}) - {}", this.getClass() ,ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
        }else{

            Map<String, Integer> cartMap = new HashMap<>();
            ordersDetailList.stream().collect(Collectors.toMap(OrdersDetail::getFId, OrdersDetail::getODNumber));
            foodService.addNumber(cartMap);
        }

        //4. 如果已支付，需要退款
        //TODO:
        return true;
    }

    @Override
    public Boolean updateOrderAddress(String orderPrimaryId, String addressId) {
        //TODO:
        return null;
    }

    @Override
    @Transactional
    public Boolean updateOrderTo_ORDER_PAID(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        //判断订单是否存在
        if (ordersPrimary == null){
            log.error("{} - {} - opid:{}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage(), orderPrimaryId);
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        //判断支付状态
        if (ordersPrimary.getOPState() != OrderStates.ORDER_GENERATED.getCode()){
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    orderPrimaryId,
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }
        //修改订单状态
        ordersPrimary.setOPState(OrderStates.ORDER_PAID.getCode());
        ordersPrimary.setOPPaymentDate(new Timestamp(System.currentTimeMillis()));
        OrdersPrimary result = orderPrimaryService.updateOrder(ordersPrimary);
        if(result == null){
            log.error("{} - {} - opid:{}", this.getClass(), ExceptionStates.ERROR_ORDER_STATE.getMessage(), orderPrimaryId);
            throw new SaleException(ExceptionStates.PAYMENT_FAIL);
        }

        return true;
    }

    @Override
    public Boolean updateOrderTo_PREPARING_FOOD(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.PREPARING_FOOD.getCode());
        ordersPrimary.setOPConfirmDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_READY_TO_DELIVER(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.READY_TO_DELIVER.getCode());
        ordersPrimary.setOPPrepareFinishDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_FOOD_DELIVERING(String orderPrimaryId, String delivererId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }

        if (delivererService.findDelivererByDId(delivererId) == null){
            log.error("({}) - {}", this.getClass() ,ExceptionStates.NO_SUCH_DELIVERER.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_DELIVERER);
        }

        ordersPrimary.setOPState(OrderStates.FOOD_DELIVERING.getCode());
        ordersPrimary.setOPStartDeliverDate(new Timestamp(System.currentTimeMillis()));
        ordersPrimary.setDId(delivererId);
        orderPrimaryService.updateOrder(ordersPrimary);

        return true;
    }

    @Override
    public Boolean updateOrderTo_FOOD_DELIVERED(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.FOOD_DELIVERED.getCode());
        ordersPrimary.setOPDeliverArriveDate(new Timestamp(System.currentTimeMillis()));
        return true;
    }

    @Override
    public Boolean updateOrderTo_USER_RECEIVED(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.USER_RECEIVED.getCode());
        ordersPrimary.setOPFinishDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_REFUNDING(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.REFUNDING.getCode());
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_ERROR_STATE(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.ERROR_STATE.getCode());
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_CANCELED_BY_SHOP(String orderPrimaryId) {
        OrdersPrimary ordersPrimary  = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null){
            log.error("{} - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        }
        ordersPrimary.setOPState(OrderStates.CANCELED_BY_SHOP.getCode());
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean validateStudentAndOrder(String studentId, String orderPrimaryId) {
        if (studentService.findStudentByStudentId(studentId).getSId() == orderPrimaryService.findOrdersById(orderPrimaryId).getOPId()){
            return true;
        }else{
            return false;
        }
    }
}
