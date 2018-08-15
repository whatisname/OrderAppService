package com.xxy.ordersystem.viewmessage.converterUtil;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.DelivererVO;
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
            //特殊属性
            boothVO.setQuyu(Quyu.areaOf(booth.getBQuyu()));
            boothVO.setState(BoothStates.stateOf(booth.getBState()));
            boothVOList.add(boothVO);
        }
        return new PageImpl<BoothVO>(boothVOList, pageable, boothPage.getTotalElements());
    }

    public static Page<DelivererVO> fromDelivererPageToVO(Page<Deliverer> delivererPage, Pageable pageable){
        List<DelivererVO> delivererVOList = new ArrayList<>();
        for (Deliverer deliverer: delivererPage.getContent()){
            DelivererVO delivererVO = new DelivererVO();
            BeanUtils.copyProperties(deliverer, delivererVO);
            //特殊属性
            delivererVO.setQuyu(Quyu.areaOf(deliverer.getDQuyu()));
            delivererVOList.add(delivererVO);
        }
        return new PageImpl<DelivererVO>(delivererVOList, pageable, delivererPage.getTotalElements());
    }
}
