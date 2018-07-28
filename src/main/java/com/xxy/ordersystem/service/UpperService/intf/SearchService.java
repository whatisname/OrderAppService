package com.xxy.ordersystem.service.UpperService.intf;

import com.xxy.ordersystem.viewmessage.viewobject.SearchResultVO;

import java.awt.print.Pageable;

/**
 * @author X
 * @package com.xxy.ordersystem.service.UpperService.intf
 * @date 7/13/2018 11:13 PM
 */
public interface SearchService {
    //    SearchResultVO searchAll(String searchText, Integer page, Integer size);
    SearchResultVO searchBooth(String searchText, Pageable pageable);
    SearchResultVO searchFood(String searchText, Pageable pageable);
}
