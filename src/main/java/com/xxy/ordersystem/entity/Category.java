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
public class Category {
    @Id
    private String cId;
    private String cName;
    private Timestamp cCreateTime;
    private Timestamp cUpdateTime;
    private String bId;

    public Category(String cId, String cName, String bId) {
        this.cId = cId;
        this.cName = cName;
        this.bId = bId;
    }

    public Category() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(cId, category.cId) &&
                Objects.equals(cName, category.cName) &&
                Objects.equals(cCreateTime, category.cCreateTime) &&
                Objects.equals(cUpdateTime, category.cUpdateTime) &&
                Objects.equals(bId, category.bId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cId, cName, cCreateTime, cUpdateTime, bId);
    }
}
