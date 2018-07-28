package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Student;
import org.springframework.stereotype.Service;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/12/2018 4:58 PM
 */

public interface StudentService {
    Student findSutdentByOpenId(String openId);
    Student findStudentByStudentId(String studentId);
    Boolean existStudent(String studentId);
}
