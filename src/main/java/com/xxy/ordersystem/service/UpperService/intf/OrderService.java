package com.xxy.ordersystem.service.UpperService.intf;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Address;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.OrdersPrimary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.intf
 * @date 7/14/2018 12:38 PM
 */
public interface OrderService {
    //==========================订单查询=========================================
    /**
     * 根据id查找单个订单
     * @param orderPrimaryId
     * @return
     */
    OrderDTO findOrderByPrimaryId(String orderPrimaryId);

    /**
     * 查询所有订单
     * @return
     */
    List<OrderDTO> findAllOrders();

    /**
     * 查询所有某一状态的订单（分页）
     * @param orderState 订单状态
     * @param haveDetail 是否有细节
     * @param pageable 分页信息
     * @return
     */
    Page<OrderDTO> findAllOrdersByState(Integer orderState, Boolean haveDetail, Pageable pageable);

    /**
     * 查询所有订单（分页）
     * @param haveDetail 是否有细节
     * @param pageable 分页信息
     * @return
     */
    Page<OrderDTO> findAllOrders(Boolean haveDetail, Pageable pageable);


    /**
     * 用户根据student id查找某个用户的所有订单
     * @param studentId
     * @param haveDetail
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrdersByStudentId(String studentId, Boolean haveDetail, Pageable pageable);

    /**
     * 用户根据studentId和orderState查找学生下某个状态的所有订单（分页）
     * @param studentId
     * @param orderState
     * @param haveDetail 是否查询订单细节（订单副表）
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrdersByStudentIdAndState(String studentId, Integer orderState, Boolean haveDetail, Pageable pageable);

    /**
     * 商家根据boothId和state查询订单
     * @param boothId
     * @return
     */
    Page<OrderDTO> findAllOrdersByBoothId(String boothId, Boolean haveDetail, Pageable pageable);

    /**
     * 商家根据boothId和state查询订单（分页）
     * @param boothId
     * @param orderState
     * @param haveDetail 是否查询订单细节（订单副表）
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrdersByBoothIdAndState(String boothId, Integer orderState, Boolean haveDetail, Pageable pageable);

    /**
     * 快递员根据delivererId查询所有订单（分页）
     * @param delivererId
     * @param haveDetail 是否查询订单细节（订单副表）
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrdersByDelivererId(String delivererId, Boolean haveDetail, Pageable pageable);

    /**
     * 快递员根据delivererId和orderState查询所有订单（分页）
     * @param delivererId
     * @param orderState
     * @param haveDetail 是否查询订单细节（订单副表）
     * @param pageable
     * @return
     */
    Page<OrderDTO> findAllOrdersByDelivererIdAndState(String delivererId, Integer orderState, Boolean haveDetail, Pageable pageable);


    //========================订单 增删改==================================
    /**
     * 订单已下达
     * 学生触发
     * 学生付款后 - 新增订单
     * @param studentId
     * @param addressId
     * @param orderDetailMap {@link Map<String, Integer>} Map<foodId, number>
     * @return Boolean
     */
    String addNewOrder(String studentId, String addressId, Map<String, Integer> orderDetailMap);

    /**
     *
     * 取消订单（数据库保留）
     * 被用户取消（用户确认收到之前，支付后取消，如无法收到订单，快递员送未送达等）
     * @param ordersPrimary
     * @param comment 用户的理由
     * @return
     */
    Boolean updateOrderTo_CANCELED_BY_USER(OrdersPrimary ordersPrimary, String comment);

    /**
     *
     * 更改配送地址
     * 允许在配送员抢单之前更改地址（状态FOOD_DELIVERING之前）
     * @param ordersPrimary
     * @param address
     * @return
     */
    Boolean updateOrderAddress(OrdersPrimary ordersPrimary, Address address);

    /**
     * 退款申请中(用户确认订单以后请求退款或投诉退款)
     * 被用户取消（用户确认收到之前，支付后取消，如无法收到订单，快递员送未送达等）
     * 用户触发
     * @param ordersPrimary
     * @param comment 用户的理由
     * @return
     */
    Boolean updateOrderTo_REFUNDING(OrdersPrimary ordersPrimary, String comment);


    /**
     * 错误状态
     * @param ordersPrimary
     * @return
     */
    Boolean updateOrderTo_ERROR_STATE(OrdersPrimary ordersPrimary, String comment);

    /**
     * 被商家取消（无法准备或无法配送）
     *
     * @param ordersPrimary
     * @return
     */
    Boolean updateOrderTo_CANCELED_BY_SHOP(OrdersPrimary ordersPrimary, String comment);

    //========================订单状态管理==================================

    /**
     * 订单支付
     * 学生触发
     * 商家接单后 - 更新订单状态为 PREPARING_FOOD
     * @param ordersPrimary
     * @return Boolean
     */
    Boolean updateOrderTo_ORDER_PAID(OrdersPrimary ordersPrimary);

    /**
     * 外卖准备中
     * 商家触发
     * 商家接单后 - 更新订单状态为 PREPARING_FOOD
     * @param ordersPrimary
     * @return Boolean
     */
    Boolean updateOrderTo_PREPARING_FOOD(OrdersPrimary ordersPrimary);

    /**
     * 可抢单
     * 商家触发
     * 商家准备完成后 - 更新订单状态为 READY_TO_DELIVER
     * @param ordersPrimary
     * @return Boolean
     */
    Boolean updateOrderTo_READY_TO_DELIVER(OrdersPrimary ordersPrimary);

    /**
     * 配送中
     * 快递员触发
     * 快递员接单后 - 更新订单状态为 FOOD_DELIVERING
     * @param ordersPrimary
     * @param deliverer
     * @return Boolean
     */
    Boolean updateOrderTo_FOOD_DELIVERING(OrdersPrimary ordersPrimary, Deliverer deliverer);

    /**
     * 外卖员已送达
     * 快递员触发
     * 快递员送到后 - 更新订单状态为FOOD_DELIVERED
     * @param ordersPrimary
     * @return
     */
    Boolean updateOrderTo_FOOD_DELIVERED(OrdersPrimary ordersPrimary);

    /**
     * 用户已接单
     * 用户触发
     * 用户点击确认后 - 更新订单状态为USER_RECEIVED
     * @param ordersPrimary
     * @return
     */
    Boolean updateOrderTo_USER_RECEIVED(OrdersPrimary ordersPrimary);



    //=========================验证管理========================
    Boolean validateStudentAndOrder(String studentId, String orderPrimaryId);

}
