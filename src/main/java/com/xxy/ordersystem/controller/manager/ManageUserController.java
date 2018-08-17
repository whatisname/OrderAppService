package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.entity.Student;
import com.xxy.ordersystem.service.intf.StudentService;
import com.xxy.ordersystem.viewmessage.converterUtil.PageToVOPage;
import com.xxy.ordersystem.viewmessage.viewobject.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 8/15/2018 8:54 PM
 */
@RestController
@RequestMapping("/manage/user")
public class ManageUserController {
    @Autowired
    private StudentService studentService;

    @GetMapping("list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            Map<String, Object> map
    ){
        PageRequest request = PageRequest.of(page, size);

        Page<Student> studentPage = studentService.findAllStudent(request);

        //装配DelivererVO
        Page<StudentVO> studentVOPage = PageToVOPage.fromStudentPageToVO(studentPage, request);

        map.put("studentVOPage", studentVOPage);
        return new ModelAndView("/manage/user_list", map);
    }
}
