package com.xxy.ordersystem.exceptionHandler;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xxy.ordersystem.constantConfig.ProjectUrlConfig;
import com.xxy.ordersystem.exception.AuthenticationException;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.exception.WebSaleException;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.exceptionHandler
 * @date 8/15/2018 9:57 AM
 */
@ControllerAdvice
public class SaleExceptionHangler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登陆异常
    @ExceptionHandler(value = AuthenticationException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleAuthorizationException(AuthenticationException e){
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getOs())
                .concat("/manage/security/loginReq"), map);
    }

    @ExceptionHandler(value = SaleException.class)
    @ResponseBody
    public ResultVO handleSaleException(SaleException e){
        return MessageUtil.error(e.getMessage(), null);
    }

    @ExceptionHandler(value = WebSaleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleWebSaleException(
            WebSaleException e,
            Map<String, Object> map
    ){
        map.put("resultVO", MessageUtil.error(e.getMessage(), null));
        return new ModelAndView("redirect:".concat(projectUrlConfig.getOs()).concat("/error"), map);
    }

}
