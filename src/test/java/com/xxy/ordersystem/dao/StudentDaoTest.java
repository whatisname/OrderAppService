package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/14/2018 2:11 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StudentDaoTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void findStudentBySOpenid() {
    }

    @Test
    public void findStudentBySId() {
        Student student = studentDao.findStudentBySId("1");
        log.info("find: {}", student.toString());
    }
}