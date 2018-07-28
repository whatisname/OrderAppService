package com.xxy.ordersystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Food {
    @Id
    private String fId;
    private String fName;
    private String fImg;
    private String fDescription;
    private Integer fNumber;
    private BigDecimal fPrice;
    private String fComment;
    private Timestamp fCreateTime;
    private Timestamp fUpdateTime;
    private String bId;
    private String cId;

    public Food(String fId, String fName, BigDecimal fPrice) {
        this.fId = fId;
        this.fName = fName;
        this.fPrice = fPrice;
    }

    public Food() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(fId, food.fId) &&
                Objects.equals(fName, food.fName) &&
                Objects.equals(fImg, food.fImg) &&
                Objects.equals(fDescription, food.fDescription) &&
                Objects.equals(fNumber, food.fNumber) &&
                Objects.equals(fPrice, food.fPrice) &&
                Objects.equals(fComment, food.fComment) &&
                Objects.equals(fCreateTime, food.fCreateTime) &&
                Objects.equals(fUpdateTime, food.fUpdateTime) &&
                Objects.equals(bId, food.bId) &&
                Objects.equals(cId, food.cId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fId, fName, fImg, fDescription, fNumber, fPrice, fComment, fCreateTime, fUpdateTime, bId, cId);
    }
}
