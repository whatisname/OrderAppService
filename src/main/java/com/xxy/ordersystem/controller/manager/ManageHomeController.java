package com.xxy.ordersystem.controller.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 9/3/2018 2:02 PM
 */
@RestController
@Slf4j
@RequestMapping("/manage/home")
public class ManageHomeController {
    @GetMapping("/home")
    public ModelAndView home(
            Map<String, Object> map
    ){
        return new ModelAndView("/manage/home", map);
    }

}
