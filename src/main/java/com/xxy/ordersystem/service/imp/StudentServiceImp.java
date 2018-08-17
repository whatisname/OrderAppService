package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.StudentDao;
import com.xxy.ordersystem.entity.Student;
import com.xxy.ordersystem.service.intf.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/12/2018 4:58 PM
 */
@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student findSutdentByOpenId(String openId) {

        return studentDao.findStudentBySOpenid(openId);
    }

    @Override
    public Student findStudentByStudentId(String studentId) {

        return studentDao.findStudentBySId(studentId);
    }

    @Override
    public Boolean existStudent(String studentId) {
        Student student = studentDao.findStudentBySId(studentId);
        if (student == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Page<Student> findAllStudent(Pageable pageable) {
        return studentDao.findAll(pageable);
    }
}
