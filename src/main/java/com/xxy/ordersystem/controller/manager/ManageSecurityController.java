package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.constantConfig.CookieConfig;
import com.xxy.ordersystem.constantConfig.ProjectUrlConfig;
import com.xxy.ordersystem.constantConfig.RedisConstant;
import com.xxy.ordersystem.entity.Manager;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.AuthenticationException;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.intf.ManagerService;
import com.xxy.ordersystem.utils.CookieUtil;
import com.xxy.ordersystem.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 8/14/2018 11:31 PM
 */
@RestController
@Slf4j
@RequestMapping("/manage/security")
public class ManageSecurityController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private ManagerService managerService;

    @GetMapping("/loginReq")
    public ModelAndView loginReq(
            Map<String, Object> map
    ) {
        return new ModelAndView("/manage/login", map);
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Map<String, Object> map,
            HttpServletResponse response,
            HttpServletRequest request
    ) {
        //获取用户
        Manager manager = managerService.findManagerByEmail(email);
        if (manager == null) {
            log.error("{} - {}", this.getClass(), "用户不存在:"+email);
            throw new AuthenticationException(ExceptionStates.NO_RESULT.getCode(), "用户不存在:"+email);
        }
        // 验证密码
        if (!manager.getMPassword().equals(password)){
            log.error("{} - {}", this.getClass(), "密码错误");
            throw new AuthenticationException(ExceptionStates.NO_RESULT.getCode(), "密码错误");
        }
        //设置token - redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE_TIME;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), email, expire, TimeUnit.SECONDS);

        //设置token - cookie
//        CookieUtil.set(response, cookieConfig.getName(), token, RedisConstant.EXPIRE_TIME);
        //设置token和UserInfo - session
        request.getSession().setAttribute(cookieConfig.getName(), token);
        request.getSession().setAttribute("user", manager);

        return new ModelAndView("redirect:" + projectUrlConfig.getOs() + "/", map);
    }

    @GetMapping("/logout")
    public ModelAndView logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, Object> map
    ) {
//        //从cookie里面查询
//        Cookie cookie = CookieUtil.get(request, cookieConfig.getName());
//        if (cookie != null){
//            //清除redis
//            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//            //清楚cookie
//
//            Cookie cookie_ = new Cookie(cookieConfig.getName(), null);
//            cookie_.setPath("/");
//            cookie_.setMaxAge(0);
//            response.addCookie(cookie);
//            map.put("resultVO", MessageUtil.success("登出成功", null));
////            return new ModelAndView("/manage/login", map);
//        }

        //从session里面查询
        HttpSession session = request.getSession();
        if (session.getAttribute(cookieConfig.getName()) != null) {
            String token = session.getAttribute(cookieConfig.getName()).toString();
            // 清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, token));
            //清除session
            request.getSession().removeAttribute(cookieConfig.getName());
            request.getSession().removeAttribute("user");
        }
        return new ModelAndView("redirect:" + projectUrlConfig.getOs() + "/manage/security/loginReq", map);
    }

}
