package com.xxy.ordersystem.viewmessage.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xxy.ordersystem.entity.Deliverer;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.viewobject.metaViewObject
 * @date 7/14/2018 12:41 PM
 */
@Data
public class OrderVO {
    @JsonProperty("id")
    private String oPId;

    @JsonProperty("deliverFee")
    private BigDecimal oPDeliverFee;
    @JsonProperty("sum")
    private BigDecimal oPSum;
    @JsonProperty("orderDate")
    private Timestamp oPOrderDate;
    @JsonProperty("oPPaymentDate")
    private Timestamp oPPaymentDate;

    @JsonProperty("oPConfirmDate")
    private Timestamp oPConfirmDate;
    @JsonProperty("oPPrepareFinishDate")
    private Timestamp oPPrepareFinishDate;
    @JsonProperty("oPStartDeliverDate")
    private Timestamp oPStartDeliverDate;
    @JsonProperty("oPDeliverArriveDate")
    private Timestamp oPDeliverArriveDate;

    @JsonProperty("finishDate")
    private Timestamp oPFinishDate;
    @JsonProperty("state")
    private Integer oPState;

    @JsonProperty("comment")
    private String oPComment;

    private String sId;
    private String bId;
    private String dId;
    private String aId;


    @JsonProperty("foods")
    private List<OrderDetailVO> orderDetailVOList;

    @JsonProperty("student")
    private StudentVO studentVO;

    @JsonProperty("booth")
    private BoothVO boothVO;


    @JsonProperty("address")
    private AddressVO addressVO;

    @JsonProperty("deliverer")
    private DelivererVO delivererVO;
}
