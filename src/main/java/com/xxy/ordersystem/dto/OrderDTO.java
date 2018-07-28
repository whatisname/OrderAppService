package com.xxy.ordersystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xxy.ordersystem.entity.*;
import lombok.Data;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dto
 * @date 7/16/2018 5:37 PM
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO extends OrdersPrimary {
    List<OrderDetailDTO> orderDetailDTOList;
    Booth booth;
    Address address;
    Student student;
    Deliverer deliverer;
}
