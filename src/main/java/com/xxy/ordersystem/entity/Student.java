package com.xxy.ordersystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Student {
    @Id
    private String sId;
    private String sName;
    private Byte sGender;
    private String sPhone;
    private String sXibieBanji;
    private String sIdcard;

    private String sStudentid;

    private String sEmail;
    private int sQuyu;
    private String sPassword;
    private String sOpenid;
    private String sComment;
    private Timestamp sCreateTime;
    private Timestamp sUpdateTime;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Student student = (Student) o;
//        return sQuyu == student.sQuyu &&
//                Objects.equals(sId, student.sId) &&
//                Objects.equals(sName, student.sName) &&
//                Objects.equals(sGender, student.sGender) &&
//                Objects.equals(sPhone, student.sPhone) &&
//                Objects.equals(sXibieBanji, student.sXibieBanji) &&
//                Objects.equals(sIdcard, student.sIdcard) &&
//                Objects.equals(sStudentid, student.sStudentid) &&
//                Objects.equals(sEmail, student.sEmail) &&
//                Objects.equals(sPassword, student.sPassword) &&
//                Objects.equals(sOpenid, student.sOpenid) &&
//                Objects.equals(sComment, student.sComment) &&
//                Objects.equals(sCreateTime, student.sCreateTime) &&
//                Objects.equals(sUpdateTime, student.sUpdateTime);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(sId, sName, sGender, sPhone, sXibieBanji, sIdcard, sStudentid, sEmail, sQuyu, sPassword, sOpenid, sComment, sCreateTime, sUpdateTime);
//    }
}
