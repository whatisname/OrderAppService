package com.xxy.ordersystem.service.UpperService.imp;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.dto.OrderDetailDTO;
import com.xxy.ordersystem.entity.*;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.service.intf.*;
import com.xxy.ordersystem.utils.AddressUtil;
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
        //get primary orderManage
        OrdersPrimary ordersPrimary = orderPrimaryService.findOrdersById(orderPrimaryId);
        if (ordersPrimary == null) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERPRIMRY.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERPRIMRY);
        } else {
            BeanUtils.copyProperties(ordersPrimary, orderDTO);

            //get detail orders
            List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(orderPrimaryId);
            if (ordersDetailList.size() == 0) {
                log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
                throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
            }
            //load student
            Student student = studentService.findStudentByStudentId(ordersPrimary.getSId());
            orderDTO.setStudent(student);

            //load address
            Address address = AddressUtil.toAddress(ordersPrimary.getOPAddress());
            orderDTO.setAddress(address);

            //load deliever

            if (ordersPrimary.getDId() != null) {
                if (!ordersPrimary.getDId().equals("")) {
                    Deliverer deliverer = delivererService.findDelivererByDId(ordersPrimary.getDId());
                    orderDTO.setDeliverer(deliverer);
                }
            }

            //load foods
            List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
            for (OrdersDetail ordersDetail : ordersDetailList) {
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
    public Page<OrderDTO> findAllOrdersByState(Integer orderState, Boolean haveDetail, Pageable pageable) {
        if (haveDetail) {
            //TODO
            return null;
        } else {
            Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByOrderState(orderState, pageable);
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        }
    }

    @Override
    public Page<OrderDTO> findAllOrders(Boolean haveDetail, Pageable pageable) {
        if (haveDetail) {
            //TODO
            return null;
        } else {
            Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAll(pageable);
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        }
    }

    @Override
    public Page<OrderDTO> findAllOrdersByStudentId(String studentId, Boolean haveDetail, Pageable pageable) {
        Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByStudentId(studentId, pageable);
        if (!haveDetail) {
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        } else {
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
        } else {
            //TODO
            return null;
        }

    }

    /**
     * 转换{@link Page<OrdersPrimary>} -> {@Link Page<OrderDTO>}
     *
     * @param ordersPrimaryPage
     * @param pageable
     * @return
     */
    private Page<OrderDTO> convertToPageOrderDTO(Page<OrdersPrimary> ordersPrimaryPage, Pageable pageable) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrdersPrimary ordersPrimary : ordersPrimaryPage.getContent()) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(ordersPrimary, orderDTO);
            orderDTOList.add(orderDTO);
        }
        return new PageImpl<OrderDTO>(orderDTOList, pageable, ordersPrimaryPage.getTotalElements());
    }

    @Override
    public Page<OrderDTO> findAllOrdersByBoothId(String boothId, Boolean haveDetail, Pageable pageable) {
        Page<OrdersPrimary> ordersPrimaryPage = orderPrimaryService.findAllByBoothId(boothId, pageable);
//        List<OrderDTO> orderDTOList = new ArrayList<>();

        if (!haveDetail) { //不包含细节
            return this.convertToPageOrderDTO(ordersPrimaryPage, pageable);
        } else {
            //TODO
            return null;
        }
    }

    @Override
    public Page<OrderDTO> findAllOrdersByBoothIdAndState(String boothId, Integer orderState, Boolean haveDetail, Pageable pageable) {
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
        if (studentService.findStudentByStudentId(studentId) == null) {
            log.error("{} - {}", getClass(), ExceptionStates.NOSUCH_STUDENT.getMessage());
            throw new SaleException(ExceptionStates.NOSUCH_STUDENT);
        }
        OrdersPrimary ordersPrimary = new OrdersPrimary();
        ordersPrimary.setOPId(KeyUtil.generateUniqueKeyId());
        ordersPrimary.setSId(studentId);
        ordersPrimary.setOPState(OrderStates.ORDER_GENERATED.getCode());

        //1. 查询商品（数量价格）
        List<String> foodIdList = new ArrayList<>(orderDetailMap.keySet());
        List<Food> foodList = foodService.findAllFoodById(foodIdList);
        if (foodList == null) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.PRODUCT_NOT_EXIST.getMessage());
            throw new SaleException(ExceptionStates.PRODUCT_NOT_EXIST);
        } else {
            if (foodList.size() == 0) {
                log.error("({}) - {}", this.getClass(), ExceptionStates.PRODUCT_NOT_EXIST.getMessage());
                throw new SaleException(ExceptionStates.PRODUCT_NOT_EXIST);
            }
        }

        // set booth id
        ordersPrimary.setBId(foodList.get(0).getBId());


        //2. 计算价格, load ordersDetailVOList
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        List<OrdersDetail> ordersDetailList = new ArrayList<>();

        for (Food food : foodList) {
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
            } else {
                log.error("({}) - {}", this.getClass(), ExceptionStates.OUT_OF_STOCK.getMessage());
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

        //4.查询，填入地址
        Address address = addressService.findAddressByAddressId(addressId);
        ordersPrimary.setOPAddress(AddressUtil.toAddressString(address));

        //5.不可填入comment（订单备注），留用取消说明，订单备注加进地址String内
//        ordersPrimary.setOPComment(address.getAComment());

        //6. 写入数据库
        OrdersPrimary opResult = orderPrimaryService.saveNewOrdersPrimary(ordersPrimary);
        List<OrdersDetail> odResult = ordersDetailService.saveAllOrderDetails(ordersDetailList);
        if (opResult == null || odResult == null) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.ORDER_CREATE_FAIL.getMessage());
            throw new SaleException(ExceptionStates.ORDER_CREATE_FAIL);
        } else {
            if (odResult.size() == 0) {
                log.error("({}) - {}", this.getClass(), ExceptionStates.ORDER_CREATE_FAIL.getMessage());
                throw new SaleException(ExceptionStates.ORDER_CREATE_FAIL);
            }
        }

        //5. 减库存(save food list)
        foodService.updateFood(foodList);

        return ordersPrimary.getOPId();
    }

    @Override
    public Boolean updateOrderTo_CANCELED_BY_USER(OrdersPrimary ordersPrimary, String comment) {
        //1. 判断订单状态
        if (!ordersPrimary.getOPState().equals(OrderStates.ORDER_GENERATED.getCode())) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+", 订单无法取消");
            throw new SaleException(ExceptionStates.CANCEL_FAIL_ERROR_STATE.getCode(), ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+", 订单无法取消");
        }
        //2. 修改订单状态，填写退单理由
        ordersPrimary.setOPState(OrderStates.CANCELED_BY_USER.getCode());
        ordersPrimary.setOPComment(comment);
        orderPrimaryService.updateOrder(ordersPrimary);
        //3. 返回库存
        //get detail orders
        List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(ordersPrimary.getOPId());
        if (ordersDetailList.size() == 0) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
        } else {
            Map<String, Integer> cartMap = new HashMap<>();
            //TODO 待调试，可能会出问题
            cartMap = ordersDetailList.stream().collect(Collectors.toMap(OrdersDetail::getFId, OrdersDetail::getODNumber));
            foodService.addNumber(cartMap);
        }
        //4. 如果已支付，需要退款(因为事件发生于付款之前，所以并不需要)
        return true;
    }

    @Override
    public Boolean updateOrderAddress(OrdersPrimary ordersPrimary, Address address) {
        //判断状态
        if (!ordersPrimary.getOPState().equals(OrderStates.READY_TO_DELIVER)
                || !ordersPrimary.getOPState().equals(OrderStates.ORDER_GENERATED)
                || !ordersPrimary.getOPState().equals(OrderStates.ORDER_PAID)
                ) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }
        //修改订单地址
        ordersPrimary.setOPAddress(AddressUtil.toAddressString(address));
        OrdersPrimary result = orderPrimaryService.updateOrder(ordersPrimary);
        if (result == null) {
            log.error("{} - {} - opid:{}", this.getClass(), ExceptionStates.ERROR_ORDER_STATE.getMessage(), ordersPrimary.getOPId());
            throw new SaleException(ExceptionStates.PAYMENT_FAIL);
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean updateOrderTo_ORDER_PAID(OrdersPrimary ordersPrimary) {
        //判断状态
        if (ordersPrimary.getOPState() != OrderStates.ORDER_GENERATED.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }
        //修改订单状态
        ordersPrimary.setOPState(OrderStates.ORDER_PAID.getCode());
        ordersPrimary.setOPPaymentDate(new Timestamp(System.currentTimeMillis()));
        OrdersPrimary result = orderPrimaryService.updateOrder(ordersPrimary);
        if (result == null) {
            log.error("{} - {} - opid:{}", this.getClass(), ExceptionStates.ERROR_ORDER_STATE.getMessage(), ordersPrimary.getOPId());
            throw new SaleException(ExceptionStates.PAYMENT_FAIL);
        }

        return true;
    }

    @Override
    public Boolean updateOrderTo_PREPARING_FOOD(OrdersPrimary ordersPrimary) {
        if (ordersPrimary.getOPState() != OrderStates.ORDER_PAID.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }
        ordersPrimary.setOPState(OrderStates.PREPARING_FOOD.getCode());
        ordersPrimary.setOPConfirmDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_READY_TO_DELIVER(OrdersPrimary ordersPrimary) {
        if (ordersPrimary.getOPState() != OrderStates.PREPARING_FOOD.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }
        ordersPrimary.setOPState(OrderStates.READY_TO_DELIVER.getCode());
        ordersPrimary.setOPPrepareFinishDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_FOOD_DELIVERING(OrdersPrimary ordersPrimary, Deliverer deliverer) {
        if (ordersPrimary.getOPState() != OrderStates.READY_TO_DELIVER.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }

        ordersPrimary.setOPState(OrderStates.FOOD_DELIVERING.getCode());
        ordersPrimary.setOPStartDeliverDate(new Timestamp(System.currentTimeMillis()));
        ordersPrimary.setDId(deliverer.getDId());
        orderPrimaryService.updateOrder(ordersPrimary);

        return true;
    }

    @Override
    public Boolean updateOrderTo_FOOD_DELIVERED(OrdersPrimary ordersPrimary) {
        if(!ordersPrimary.getOPState().equals(OrderStates.FOOD_DELIVERING)
                || !ordersPrimary.getOPState().equals(OrderStates.USER_RECEIVED)){
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }

        //如果状态未配送中，说明用户未接单，可以把状态改为配送到
        if(ordersPrimary.getOPState().equals(OrderStates.FOOD_DELIVERING)){
            ordersPrimary.setOPState(OrderStates.FOOD_DELIVERED.getCode());
            ordersPrimary.setOPDeliverArriveDate(new Timestamp(System.currentTimeMillis()));
        }
        //如果状态为用户已接单，说明用户先确认了接单，这时不需要更改状态为配送到，只需要设置时间为用户确认接单的时间即可
        if(ordersPrimary.getOPState().equals(OrderStates.USER_RECEIVED)){
            ordersPrimary.setOPDeliverArriveDate(ordersPrimary.getOPConfirmDate());
        }
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_USER_RECEIVED(OrdersPrimary ordersPrimary) {
        if (ordersPrimary.getOPState() != OrderStates.FOOD_DELIVERING.getCode()
                && ordersPrimary.getOPState() != OrderStates.FOOD_DELIVERED.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.ERROR_ORDER_STATE.getMessage(),
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE);
        }

        ordersPrimary.setOPState(OrderStates.USER_RECEIVED.getCode());
        ordersPrimary.setOPFinishDate(new Timestamp(System.currentTimeMillis()));
        orderPrimaryService.updateOrder(ordersPrimary);
        return true;
    }

    @Override
    public Boolean updateOrderTo_REFUNDING(OrdersPrimary ordersPrimary, String comment) {
        //1. 判断订单状态()
        if (ordersPrimary.getOPState() == OrderStates.ORDER_GENERATED.getCode()) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+"订单未支付，不可退款。",
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
//            throw new SaleException(ExceptionStates.ERROR_ORDER_STATE.getCode(), ExceptionStates.ERROR_ORDER_STATE.getMessage()+"订单未支付，不可退款。");
            throw new SaleException(ExceptionStates.CANCEL_FAIL_ERROR_STATE.getCode(), ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+", 订单未支付，无法退款。");
        }

        //2. 修改订单状态，填写退款理由
        ordersPrimary.setOPState(OrderStates.CANCELED_BY_USER.getCode());
        ordersPrimary.setOPComment(comment);
        orderPrimaryService.updateOrder(ordersPrimary);
        //3. 返回库存
        //get detail orders
        List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(ordersPrimary.getOPId());
        if (ordersDetailList.size() == 0) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
        } else {
            Map<String, Integer> cartMap = new HashMap<>();
            //TODO 待调试，可能会出问题
            cartMap = ordersDetailList.stream().collect(Collectors.toMap(OrdersDetail::getFId, OrdersDetail::getODNumber));
            foodService.addNumber(cartMap);
        }
        //4. 如果已支付，需要退款(因为需要管理员处理，所以并不需要自动退款)
        return true;
    }

    @Override
    public Boolean updateOrderTo_ERROR_STATE(OrdersPrimary ordersPrimary, String comment) {
        //1. 判断订单状态
        //无需判断状态
        //2. 修改订单状态，填写错误理由
        ordersPrimary.setOPState(OrderStates.ERROR_STATE.getCode());
        ordersPrimary.setOPComment(comment);
        orderPrimaryService.updateOrder(ordersPrimary);
        //3. 返回库存
        //get detail orders
        List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(ordersPrimary.getOPId());
        if (ordersDetailList.size() == 0) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
        } else {
            Map<String, Integer> cartMap = new HashMap<>();
            //TODO 待调试，可能会出问题
            cartMap = ordersDetailList.stream().collect(Collectors.toMap(OrdersDetail::getFId, OrdersDetail::getODNumber));
            foodService.addNumber(cartMap);
        }
        //4. 如果已支付，需要退款
        //TODO 可能需要自动退款
        return true;
    }

    @Override
    public Boolean updateOrderTo_CANCELED_BY_SHOP(OrdersPrimary ordersPrimary, String comment) {
        //1. 判断订单状态
        if (!ordersPrimary.getOPState().equals(OrderStates.PREPARING_FOOD.getCode())
                && !ordersPrimary.getOPState().equals(OrderStates.ORDER_PAID.getCode())
                && !ordersPrimary.getOPState().equals(OrderStates.READY_TO_DELIVER.getCode())
                ) {
            log.error("{} - {} - opid:{} - opstate:{}",
                    this.getClass(),
                    ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+"订单目前不可被商家取消。",
                    ordersPrimary.getOPId(),
                    ordersPrimary.getOPState());
//            throw new SaleException(ExceptionStates.CANCEL_FAIL_ERROR_STATE.getCode(), ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage());
            throw new SaleException(ExceptionStates.CANCEL_FAIL_ERROR_STATE.getCode(), ExceptionStates.CANCEL_FAIL_ERROR_STATE.getMessage()+"订单目前不可被商家取消。");

        }
        //2. 修改订单状态，填写退款理由
        ordersPrimary.setOPState(OrderStates.CANCELED_BY_SHOP.getCode());
        ordersPrimary.setOPComment(comment);
        orderPrimaryService.updateOrder(ordersPrimary);
        //3. 返回库存
        //get detail orders
        List<OrdersDetail> ordersDetailList = ordersDetailService.findAllByOrderPrimaryId(ordersPrimary.getOPId());
        if (ordersDetailList.size() == 0) {
            log.error("({}) - {}", this.getClass(), ExceptionStates.NO_SUCH_ORDERDETAIL.getMessage());
            throw new SaleException(ExceptionStates.NO_SUCH_ORDERDETAIL);
        } else {
            Map<String, Integer> cartMap = new HashMap<>();
            //TODO 待调试，可能会出问题
            cartMap = ordersDetailList.stream().collect(Collectors.toMap(OrdersDetail::getFId, OrdersDetail::getODNumber));
            foodService.addNumber(cartMap);
        }
        //4. 如果已支付，需要退款(因为需要管理员处理，所以并不需要自动退款)
        //TODO 可能需要自动退款
        return true;
    }

    @Override
    public Boolean validateStudentAndOrder(String studentId, String orderPrimaryId) {
        if (studentService.findStudentByStudentId(studentId).getSId() == orderPrimaryService.findOrdersById(orderPrimaryId).getOPId()) {
            return true;
        } else {
            return false;
        }
    }
}
