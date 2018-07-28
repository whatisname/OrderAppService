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
public class Booth {
    @Id
    private String bId;
    private String bName;
    private String bCanteen;
    private String bWindow;
    private int bQuyu;
    private String bImg;
    private Integer bState;
    private String bOwnerName;
    private String bOwnerPhone;
    private String bOwnerEmail;
    private String bOwnerPassword;
    private String bOpenid;
    private String bComment;
    private Timestamp bCreateTime;
    private Timestamp bUpdateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booth booth = (Booth) o;
        return bQuyu == booth.bQuyu &&
                Objects.equals(bId, booth.bId) &&
                Objects.equals(bName, booth.bName) &&
                Objects.equals(bCanteen, booth.bCanteen) &&
                Objects.equals(bWindow, booth.bWindow) &&
                Objects.equals(bImg, booth.bImg) &&
                Objects.equals(bState, booth.bState) &&
                Objects.equals(bOwnerName, booth.bOwnerName) &&
                Objects.equals(bOwnerPhone, booth.bOwnerPhone) &&
                Objects.equals(bOwnerEmail, booth.bOwnerEmail) &&
                Objects.equals(bOwnerPassword, booth.bOwnerPassword) &&
                Objects.equals(bOpenid, booth.bOpenid) &&
                Objects.equals(bComment, booth.bComment) &&
                Objects.equals(bCreateTime, booth.bCreateTime) &&
                Objects.equals(bUpdateTime, booth.bUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bId, bName, bCanteen, bWindow, bQuyu, bImg, bState, bOwnerName, bOwnerPhone, bOwnerEmail, bOwnerPassword, bOpenid, bComment, bCreateTime, bUpdateTime);
    }
}
