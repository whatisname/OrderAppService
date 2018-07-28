package com.xxy.ordersystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author X
 * @package com.xxy.ordersystem.entity
 * @date 7/12/2018 10:52 PM
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "orders_primary", schema = "ordersystem", catalog = "")
public class OrdersPrimary {
    @Id
    @Column(name = "o_p_id")
    private String oPId;
    @Column(name = "o_p_deliver_fee")
    private BigDecimal oPDeliverFee;
    @Column(name = "o_p_sum")
    private BigDecimal oPSum;
    @Column(name = "o_p_order_date")
//    @JsonSerialize(using = Timestamp2LongSerializer.class)
    private Timestamp oPOrderDate;
    @Column(name = "o_p_payment_date")
    private Timestamp oPPaymentDate;
    @Column(name = "o_p_confirm_date")
    private Timestamp oPConfirmDate;
    @Column(name = "o_p_prepare_finish_date")
    private Timestamp oPPrepareFinishDate;
    @Column(name = "o_p_start_deliver_date")
    private Timestamp oPStartDeliverDate;
    @Column(name = "o_p_deliver_arrive_date")
    private Timestamp oPDeliverArriveDate;
    @Column(name = "o_p_finish_date")
    private Timestamp oPFinishDate;
    @Column(name = "o_p_state")
    private Integer oPState;
    private String sId;
    private String bId;
    private String dId;
    private String aId;
    @Column(name = "o_p_comment")
    private String oPComment;


    public OrdersPrimary() {
    }

    public OrdersPrimary(String oPId, BigDecimal oPDeliverFee, BigDecimal oPSum, Timestamp oPOrderDate, Timestamp oPPaymentDate, Timestamp oPConfirmDate, Timestamp oPPrepareFinishDate, Timestamp oPStartDeliverDate, Timestamp oPDeliverArriveDate, Timestamp oPFinishDate, Integer oPState, String sId, String bId, String dId, String aId, String oPComment) {
        this.oPId = oPId;
        this.oPDeliverFee = oPDeliverFee;
        this.oPSum = oPSum;
        this.oPOrderDate = oPOrderDate;
        this.oPPaymentDate = oPPaymentDate;
        this.oPConfirmDate = oPConfirmDate;
        this.oPPrepareFinishDate = oPPrepareFinishDate;
        this.oPStartDeliverDate = oPStartDeliverDate;
        this.oPDeliverArriveDate = oPDeliverArriveDate;
        this.oPFinishDate = oPFinishDate;
        this.oPState = oPState;
        this.sId = sId;
        this.bId = bId;
        this.dId = dId;
        this.aId = aId;
        this.oPComment = oPComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersPrimary that = (OrdersPrimary) o;
        return Objects.equals(oPId, that.oPId) &&
                Objects.equals(oPDeliverFee, that.oPDeliverFee) &&
                Objects.equals(oPSum, that.oPSum) &&
                Objects.equals(oPOrderDate, that.oPOrderDate) &&
                Objects.equals(oPPaymentDate, that.oPPaymentDate) &&
                Objects.equals(oPConfirmDate, that.oPConfirmDate) &&
                Objects.equals(oPPrepareFinishDate, that.oPPrepareFinishDate) &&
                Objects.equals(oPStartDeliverDate, that.oPStartDeliverDate) &&
                Objects.equals(oPDeliverArriveDate, that.oPDeliverArriveDate) &&
                Objects.equals(oPFinishDate, that.oPFinishDate) &&
                Objects.equals(oPState, that.oPState) &&
                Objects.equals(sId, that.sId) &&
                Objects.equals(bId, that.bId) &&
                Objects.equals(dId, that.dId) &&
                Objects.equals(aId, that.aId) &&
                Objects.equals(oPComment, that.oPComment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oPId, oPDeliverFee, oPSum, oPOrderDate, oPPaymentDate, oPConfirmDate, oPPrepareFinishDate, oPStartDeliverDate, oPDeliverArriveDate, oPFinishDate, oPState, sId, bId, dId, aId, oPComment);
    }
}
