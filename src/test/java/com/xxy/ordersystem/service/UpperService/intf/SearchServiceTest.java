package com.xxy.ordersystem.service.UpperService.intf;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.imp
 * @date 7/14/2018 1:27 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void search() {
//        SearchResultVO searchResultVO = searchService.search("阴");
//        log.info("搜索结果：{}", searchResultVO.toString());
    }
}