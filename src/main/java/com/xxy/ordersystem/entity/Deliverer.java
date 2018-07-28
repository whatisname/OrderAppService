package com.xxy.ordersystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Deliverer {
    @Id
    private String dId;
    private String dName;
    private String dPhone;
    private String dXibieBanji;
    private String dIdcard;
    private String dEmail;
    private int dQuyu;
    private String dPassword;
    private String dComment;
    private Timestamp dCreateTime;
    private Timestamp dUpdateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliverer deliverer = (Deliverer) o;
        return dQuyu == deliverer.dQuyu &&
                Objects.equals(dId, deliverer.dId) &&
                Objects.equals(dName, deliverer.dName) &&
                Objects.equals(dPhone, deliverer.dPhone) &&
                Objects.equals(dXibieBanji, deliverer.dXibieBanji) &&
                Objects.equals(dIdcard, deliverer.dIdcard) &&
                Objects.equals(dEmail, deliverer.dEmail) &&
                Objects.equals(dPassword, deliverer.dPassword) &&
                Objects.equals(dComment, deliverer.dComment) &&
                Objects.equals(dCreateTime, deliverer.dCreateTime) &&
                Objects.equals(dUpdateTime, deliverer.dUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(dId, dName, dPhone, dXibieBanji, dIdcard, dEmail, dQuyu, dPassword, dComment, dCreateTime, dUpdateTime);
    }
}
