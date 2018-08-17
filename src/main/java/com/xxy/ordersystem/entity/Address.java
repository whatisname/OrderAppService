package com.xxy.ordersystem.entity;

import com.xxy.ordersystem.utils.KeyUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author X
 * @package com.xxy.ordersystem.entity.temp
 * @date 7/16/2018 9:41 PM
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Address {
    @Id
    private String aId;
    private int aQuyu;
    private String aDormintory;
    private String aAddress;
    private Boolean aDefault;
    private String aComment;
    private Timestamp aCreateTime;
    private Timestamp aUpdateTime;
    private String sId;
    private String aName;
    private String aPhone;

    public Address(int aQuyu, String aDormintory, String aAddress, Boolean aDefault, String aComment, Timestamp aCreateTime, Timestamp aUpdateTime, String sId, String aName, String aPhone) {
        this.aQuyu = aQuyu;
        this.aDormintory = aDormintory;
        this.aAddress = aAddress;
        this.aDefault = aDefault;
        this.aComment = aComment;
        this.aCreateTime = aCreateTime;
        this.aUpdateTime = aUpdateTime;
        this.sId = sId;
        this.aName = aName;
        this.aPhone = aPhone;
//        this.aId = KeyUtil.generateUniqueKeyId();
    }

    public Address() {
//        this.aId = KeyUtil.generateUniqueKeyId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return aQuyu == address.aQuyu &&
                Objects.equals(aId, address.aId) &&
                Objects.equals(aDormintory, address.aDormintory) &&
                Objects.equals(aAddress, address.aAddress) &&
                Objects.equals(aDefault, address.aDefault) &&
                Objects.equals(aComment, address.aComment) &&
                Objects.equals(aCreateTime, address.aCreateTime) &&
                Objects.equals(aUpdateTime, address.aUpdateTime) &&
                Objects.equals(sId, address.sId) &&
                Objects.equals(aName, address.aName) &&
                Objects.equals(aPhone, address.aPhone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(aId, aQuyu, aDormintory, aAddress, aDefault, aComment, aCreateTime, aUpdateTime, sId, aName, aPhone);
    }
}
