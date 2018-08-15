package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.constantConfig.CookieConfig;
import com.xxy.ordersystem.constantConfig.ProjectUrlConfig;
import com.xxy.ordersystem.constantConfig.RedisConstant;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ManageSecurity {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    @Autowired
    private CookieConfig cookieConfig;

    @GetMapping("/loginReq")
    public ModelAndView loginReq(
            Map<String, Object> map
    ){
        return new ModelAndView("/manage/login", map);
    }

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            Map<String, Object> map,
            HttpServletResponse response
    ){
        //获取用户
        if (!name.equals("admin")){
            log.info("{} - {}", this.getClass(), "用户名不存在："+name);
            throw new SaleException(ExceptionStates.NO_RESULT.getCode(), "用户名不存在："+name);
        }
        //设置token - redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE_TIME;

        //openid
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), name, expire, TimeUnit.SECONDS);
        //设置token - cookie
        Cookie cookie = new Cookie(cookieConfig.getName(), token);
        cookie.setPath("/");
        cookie.setMaxAge(RedisConstant.EXPIRE_TIME);
        response.addCookie(cookie);

        return new ModelAndView("redirect:"+projectUrlConfig.getOs()+"/", map);
    }

    @GetMapping("/logout")
    public ModelAndView logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Map<String, Object> map
    ){
        //从cookie里面查询
        Cookie cookie = null;
        if (request.getCookies().length != 0) {
            for (Cookie cookie_ : request.getCookies()) {
                if(cookie_.getName().equals(cookieConfig.getName())){
                    cookie = cookie_;
                    break;
                }
            }
        }
        if (cookie != null){
            //清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清楚cookie

            Cookie cookie_ = new Cookie(cookieConfig.getName(), null);
            cookie_.setPath("/");
            cookie_.setMaxAge(0);
            response.addCookie(cookie);
            map.put("resultVO", MessageUtil.success("登出成功", null));
//            return new ModelAndView("/manage/login", map);
        }
        return new ModelAndView("redirect:"+ projectUrlConfig.getOs()+"/manage/security/loginReq", map);
    }

}
