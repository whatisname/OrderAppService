package com.xxy.ordersystem.viewmessage.converterUtil;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.viewmessage.converterUtil
 * @date 8/9/2018 5:25 PM
 */
public class PageToVOPage {
    public static Page<BoothVO> fromBoothPageToVO(Page<Booth> boothPage, Pageable pageable){
        List<BoothVO> boothVOList = new ArrayList<>();
        for (Booth booth: boothPage.getContent()){
            BoothVO boothVO = new BoothVO();
            BeanUtils.copyProperties(booth, boothVO);
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));
            boothVO.setState(BoothStates.stateOf(booth.getBState()));
            boothVOList.add(boothVO);
        }
        return new PageImpl<BoothVO>(boothVOList, pageable, boothPage.getTotalElements());
    }
}
