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
 * @date 9/3/2018 7:44 AM
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Data
public class Feedback {
    @Id
    private String fbId;
    private String sId;
    private String fbTitle;
    private String fbContent;
    private String fbImg;
    private int fbState;
    private Timestamp fbCreateTime;
    private Timestamp fbUpdateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return fbState == feedback.fbState &&
                Objects.equals(fbId, feedback.fbId) &&
                Objects.equals(sId, feedback.sId) &&
                Objects.equals(fbTitle, feedback.fbTitle) &&
                Objects.equals(fbContent, feedback.fbContent) &&
                Objects.equals(fbImg, feedback.fbImg) &&
                Objects.equals(fbCreateTime, feedback.fbCreateTime) &&
                Objects.equals(fbUpdateTime, feedback.fbUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fbId, sId, fbTitle, fbContent, fbImg, fbState, fbCreateTime, fbUpdateTime);
    }
}
