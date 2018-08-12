package com.xxy.ordersystem.controller.owner;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.service.UpperService.intf.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 卖家端订单管理
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
    ){
        PageRequest request = PageRequest.of(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findAllOrdersByBoothId(bid, false, request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("boolVal", true);
        map.put("date", new Timestamp(System.currentTimeMillis()));
        return new ModelAndView("/list", map);
    }
}
