package com.xxy.ordersystem.viewmessage.converterUtil;

import com.xxy.ordersystem.dto.OrderDTO;
import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.OrdersPrimary;
import com.xxy.ordersystem.entity.Student;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.OrderStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.DelivererVO;
import com.xxy.ordersystem.viewmessage.viewobject.OrderVO;
import com.xxy.ordersystem.viewmessage.viewobject.StudentVO;
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
    /**
     *
     * @param boothPage
     * @param pageable
     * @return
     */
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

    /**
     *
     * @param delivererPage
     * @param pageable
     * @return
     */
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

    /**
     *
     * @param orderDTOPage
     * @param pageable
     * @return
     */
    public static Page<OrderVO> fromOrderDTOPageToVO(Page<OrderDTO> orderDTOPage, Pageable pageable){
        List<OrderVO> orderVOList = new ArrayList<>();
        for (OrderDTO orderDTO: orderDTOPage.getContent()){
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orderDTO, orderVO);
            //特殊属性
            orderVO.setState(OrderStates.stateOf(orderDTO.getOPState()));
            //特殊子类
            if (orderDTO.getOrderDetailDTOList() != null){
                //TODO
            }
            if (orderDTO.getAddress() != null){
                //TODO
            }
            if (orderDTO.getBooth() != null){
                //TODO
            }
            if (orderDTO.getDeliverer() != null){
                //TODO
            }
            if (orderDTO.getStudent() != null){
                //TODO
            }
            orderVOList.add(orderVO);
        }
        return new PageImpl<OrderVO>(orderVOList, pageable, orderDTOPage.getTotalElements());
    }

    /**
     *
     * @param studentPage
     * @param pageable
     * @return
     */
    public static Page<StudentVO> fromStudentPageToVO(Page<Student> studentPage, Pageable pageable){
        List<StudentVO> studentVOList = new ArrayList<>();
        for (Student student: studentPage.getContent()){
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            studentVOList.add(studentVO);
        }
        return new PageImpl<StudentVO>(studentVOList, pageable, studentPage.getTotalElements());
    }
}
