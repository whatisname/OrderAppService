package com.xxy.ordersystem.aspect;

import com.xxy.ordersystem.constantConfig.CookieConfig;
import com.xxy.ordersystem.constantConfig.RedisConstant;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.exception.AuthenticationException;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author X
 * @package com.xxy.ordersystem.aspect
 * @date 8/15/2018 9:15 AM
 */
@Aspect
@Component
@Slf4j
public class ManagerAuthorizeAspect {
    @Autowired
    private CookieConfig cookieConfig;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.xxy.ordersystem.controller.manager.Manage*.*(..))" +
            "&& !execution(public * com.xxy.ordersystem.controller.manager.ManageSecurityController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        //查询cookie
//        Cookie cookie = CookieUtil.get(request, cookieConfig.getName());
//        if (cookie == null) { // 没有登陆
//            log.warn("【登陆校验】{} - Cookie中查不到token"+getClass());
//            throw new SaleException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "Cookie中查不到token");
//        }

        //查询session
        HttpSession session = request.getSession();
        String token = null;
        if (session.getAttribute(cookieConfig.getName()) != null) {
            token = request.getSession().getAttribute(cookieConfig.getName()).toString();
            //查询redis
            String tokenValue = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, token));
            if (StringUtils.isEmpty(tokenValue)) {
                log.warn("【登陆校验】{} - Redis中查不到token", getClass());
                throw new AuthenticationException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "Redis中查不到token");
            }
        }else{ // 没有登陆
            log.warn("【登陆校验】{} - Session中查不到token" + getClass());
            throw new AuthenticationException(ExceptionStates.UNAUTHORIZED_ACCESS.getCode(), "Session中查不到token");
        }
    }

}
