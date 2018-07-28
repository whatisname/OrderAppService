package com.xxy.ordersystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "orders_detail", schema = "ordersystem", catalog = "")
public class OrdersDetail {
    @Id
    @Column(name = "o_d_id")
    private String oDId;
    @Column(name = "o_d_number")
    private int oDNumber;
    @Column(name = "o_d_price")
    private BigDecimal oDPrice;
    @Column(name = "o_d_sum")
    private BigDecimal oDSum;
    @Column(name = "f_id")
    private String fId;
    @Column(name = "o_p_id")
    private String oPId;

    public OrdersDetail(String oDId, int oDNumber, BigDecimal oDPrice, BigDecimal oDSum, String fId, String oPId) {
        this.oDId = oDId;
        this.oDNumber = oDNumber;
        this.oDPrice = oDPrice;
        this.oDSum = oDSum;
        this.fId = fId;
        this.oPId = oPId;
    }

    public OrdersDetail() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersDetail that = (OrdersDetail) o;
        return oDNumber == that.oDNumber &&
                Objects.equals(oDId, that.oDId) &&
                Objects.equals(oDPrice, that.oDPrice) &&
                Objects.equals(oDSum, that.oDSum) &&
                Objects.equals(fId, that.fId) &&
                Objects.equals(oPId, that.oPId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oDId, oDNumber, oDPrice, oDSum, fId, oPId);
    }
}
