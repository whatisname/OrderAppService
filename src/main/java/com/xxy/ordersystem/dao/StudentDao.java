package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/12/2018 4:24 PM
 */

public interface StudentDao extends JpaRepository<Student, String> {
    Student findStudentBySOpenid(String openId);
    Student findStudentBySId(String studentId);
    Page<Student> findAll(Pageable pageable);

}
