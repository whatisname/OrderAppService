package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.entity.Deliverer;
import com.xxy.ordersystem.entity.Food;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.Quyu;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.form.BoothForm;
import com.xxy.ordersystem.form.DelivererForm;
import com.xxy.ordersystem.service.UpperService.intf.FileHandlerService;
import com.xxy.ordersystem.service.intf.BoothService;
import com.xxy.ordersystem.service.intf.DelivererService;
import com.xxy.ordersystem.service.intf.FoodService;
import com.xxy.ordersystem.utils.KeyUtil;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import com.xxy.ordersystem.viewmessage.UploadFileResponse;
import com.xxy.ordersystem.viewmessage.converterUtil.PageToVOPage;
import com.xxy.ordersystem.viewmessage.viewobject.BoothVO;
import com.xxy.ordersystem.viewmessage.viewobject.DelivererVO;
import com.xxy.ordersystem.viewmessage.viewobject.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller
 * @date 7/12/2018 11:33 PM
 */
@RestController
@RequestMapping("/manage/deliver")
@Slf4j
public class ManageDelivererController {
    @Autowired
    private DelivererService delivererService;
    @Autowired
    private FileHandlerService fileHandlerService;

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            Map<String, Object> map
    ) {
        PageRequest request = PageRequest.of(page, size);

        //查找快递员
        Page<Deliverer> delivererPage = delivererService.findAllDeliverer(request);

        //装配DelivererVO
        Page<DelivererVO> delivererVOPage = PageToVOPage.fromDelivererPageToVO(delivererPage, request);

        //TODO 查询送单量
        map.put("delivererVOPage", delivererVOPage);
        return new ModelAndView("/manage/deliver_list", map);
    }

    //传入booth id返回商店详情
    @GetMapping("/detail")
    public ModelAndView detail(
            @RequestParam("did") String did,
            Map<String, Object> map
    ) {
        DelivererVO delivererVO = new DelivererVO();
        Deliverer deliverer = delivererService.findDelivererByDId(did);

        if (deliverer == null) {
            log.error("{} - {}", getClass(), "did不存在");
            throw new SaleException(ExceptionStates.ID_NOT_EXIST);
        } else {
            BeanUtils.copyProperties(deliverer, delivererVO);
            delivererVO.setQuyu(Quyu.areaOf(deliverer.getDQuyu()));
            map.put("delivererVO", delivererVO);
        }
        return new ModelAndView("/manage/deliver_update", map);
    }

    //TODO 查询送单量

    @GetMapping("/delete")
    public ResultVO delete(
            @RequestParam("did") String did
    ) {
        Boolean result = delivererService.disableDeliverer(did);
        if (result == true) {
            return MessageUtil.success();
        } else {
            return MessageUtil.error("目标不存在或删除失败", null);
        }
    }

    @PostMapping("/add")
    public ModelAndView add(
            @Valid DelivererForm delivererForm,
            BindingResult bindingResult,
            Map<String, Object> map,
            @RequestParam("dAccountState") Integer state,
            @RequestParam("dPassword") String password
    ) {
        String did = KeyUtil.generateUniqueKeyId();
        Deliverer deliverer = new Deliverer();
        delivererForm.setDId(did);
        BeanUtils.copyProperties(delivererForm, deliverer);
        deliverer.setDAccountState(state);
        deliverer.setDPassword(password);
        //defaultImg --> database
//        booth.setBImg("/os/static/images/booth-default.png");
//        booth.setBOpenid("0");

        delivererService.addDeliverer(deliverer);
        map.put("did", did);

        return new ModelAndView("redirect:/manage/deliver/detail", map);
    }

    @GetMapping("/addReq")
    public ModelAndView addReq(
            Map<String, Object> map
    ) {
        return new ModelAndView("/manage/deliver_add", map);
    }

    @PostMapping("/update")
    public ModelAndView update(
            Map<String, Object> map,
            @Valid DelivererForm delivererForm,
            BindingResult bindingResult,
            @RequestParam("dQuyu") Integer quyu
    ) {
        String did = delivererForm.getDId();
        Deliverer deliverer = delivererService.findDelivererByDId(did);
        if (deliverer == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            throw new SaleException(ExceptionStates.ID_NOT_EXIST);
        }

        BeanUtils.copyProperties(delivererForm, deliverer);

        Boolean result = delivererService.updateDeliverer(deliverer);

        map.put("did", did);
        return new ModelAndView("redirect:/manage/deliver/detail", map);
    }

    @PostMapping("/updatePassword")
    public ModelAndView updatePassword(
            Map<String, Object> map,
            @RequestParam("dPassword") String password,
            @RequestParam("dId") String did
    ) {
        Deliverer deliverer = delivererService.findDelivererByDId(did);
        if (deliverer == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            throw new SaleException(ExceptionStates.ID_NOT_EXIST);
        }

        deliverer.setDPassword(password);
        Boolean result = delivererService.updateDeliverer(deliverer);
        map.put("did", did);
        return new ModelAndView("redirect:/manage/deliver/detail", map);
    }

//    @PostMapping("/updateImg")
//    public ModelAndView updateImg(
//            Map<String, Object> map,
//            @RequestParam("bImg") String img,
//            @RequestParam("bId") String bid,
//            @RequestParam("file") MultipartFile file
//    ) {
////        Booth booth = boothService.findBoothById(bid);
////        if (booth == null) {
////            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
////            return new ModelAndView("/error/404", map);
////        }
//
//        //文件不能为空
//        if (file.isEmpty()) {
//            log.error("{} - {}", getClass(), "文件不能为空");
//            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "文件不能为空");
//        }
//
//        //判断大小
//        if (file.getSize() > 1024 * 1024) {
//            log.error("{} - {}", getClass(), "文件大小超过2M");
//            throw new SaleException(ExceptionStates.PARAM_ERROR.getCode(), "文件大小超过2M");
//        }
//
//        UploadFileResponse response = fileHandlerService.saveBoothImg(file, bid);
//
////        booth.setBImg("/os/static/images/boothImg/"+response.getFileName()+"."+response.getFileExtension());
////        Boolean result = boothService.updateBooth(booth);
//        map.put("bid", bid);
//        return new ModelAndView("redirect:/manage/booth/detail", map);
//    }

    @PostMapping("/updateState")
    public ModelAndView updateState(
            Map<String, Object> map,
            @RequestParam("dAccountState") Integer state,
            @RequestParam("dId") String did
    ) {

        Deliverer deliverer = delivererService.findDelivererByDId(did);
        if (deliverer == null) {
            log.error("{} - {}", this.getClass(), ExceptionStates.ID_NOT_EXIST.getMessage());
            throw new SaleException(ExceptionStates.ID_NOT_EXIST);
        }

        deliverer.setDAccountState(state);
        Boolean result = delivererService.updateDeliverer(deliverer);
        map.put("did", did);
        return new ModelAndView("redirect:/manage/deliver/detail", map);
    }




}
