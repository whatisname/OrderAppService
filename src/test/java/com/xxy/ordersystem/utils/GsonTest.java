package com.xxy.ordersystem.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xxy.ordersystem.viewmessage.viewobject.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.utils
 * @date 7/19/2018 12:28 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GsonTest {

    @Test
    public void gson() {
        String str = "[{'id':'1','number':5},{'id':'3','number':1}]";
        Gson gson = new Gson();
//        List<CartVO> result = new Gson().fromJson("[{'id':'1','number':5},{'id':'3','number':1}]", List.class);
        List<CartVO> result = gson.fromJson(str,
                new TypeToken<List<CartVO>>(){}.getType());
//            cartMap = gson.fromJson(orderForm.getItems(), Map.class);
        log.info("{}", result);
    }
}
