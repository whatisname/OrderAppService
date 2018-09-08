package com.xxy.ordersystem.controller.manager;

import com.sun.corba.se.spi.ior.ObjectKey;
import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.converterUtil.PageToVOPage;
import com.xxy.ordersystem.viewmessage.viewobject.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 8/15/2018 7:19 PM
 */
@RestController
@Slf4j
@RequestMapping("/manage/order")
public class ManageOrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 获取所有订单（忽略细节）
     *
     * @param page
     * @param size
     * @param state
     * @return
     */
    @GetMapping("/list")
    public ModelAndView getAllOrders(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            Map<String, Object> map
    ) {
        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = null;
        if (state == -1) {
            orderDTOPage = orderService.findAllOrders(false, request);
        } else {
            orderDTOPage = orderService.findAllOrdersByState(state, false, request);
        }

        Page<OrderVO> orderVOPage = PageToVOPage.fromOrderDTOPageToVO(orderDTOPage, request);
        map.put("orderVOPage", orderVOPage);
        return new ModelAndView("/manage/order_list", map);
    }

    @GetMapping("/addReq")
    public ModelAndView addReq(
            Map<String, Object> map
    ) {
        return new ModelAndView("/manage/order_add", map);
    }

    @GetMapping("/updateReq")
    public ModelAndView changeStateReq(
            Map<String, Object> map
    ) {
        return new ModelAndView("/manage/order_update", map);
    }
}
