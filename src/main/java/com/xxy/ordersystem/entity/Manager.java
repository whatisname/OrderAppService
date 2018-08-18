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
 * @date 8/17/2018 5:08 AM
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Data
public class Manager {
    @Id
    private String mId;
    private String mName;
    private String mEmail;
    private String mPassword;
    private Timestamp mCreateTime;
    private Timestamp mUpdateTime;

    public Manager() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(mId, manager.mId) &&
                Objects.equals(mName, manager.mName) &&
                Objects.equals(mEmail, manager.mEmail) &&
                Objects.equals(mPassword, manager.mPassword) &&
                Objects.equals(mCreateTime, manager.mCreateTime) &&
                Objects.equals(mUpdateTime, manager.mUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mName, mEmail, mPassword, mCreateTime, mUpdateTime);
    }
}
