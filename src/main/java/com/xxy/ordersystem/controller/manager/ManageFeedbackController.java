package com.xxy.ordersystem.controller.manager;

import com.xxy.ordersystem.entity.Feedback;
import com.xxy.ordersystem.enums.FeedbackStates;
import com.xxy.ordersystem.service.intf.FeedbackService;
import com.xxy.ordersystem.utils.MessageUtil;
import com.xxy.ordersystem.viewmessage.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author X
 * @package com.xxy.ordersystem.controller.manager
 * @date 9/3/2018 7:52 AM
 */
@RestController
@RequestMapping("/manage/feedback")
@Slf4j
public class ManageFeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/listPage")
    public ModelAndView listPage(
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            Map<String, Object> map
    ){
        Pageable request = PageRequest.of(page, size);
        Page<Feedback> feedbackPage;
        if (state == -1){
            feedbackPage = feedbackService.findAll(request);
        }else{
            feedbackPage = feedbackService.findAllWithState(state, request);
        }
        map.put("feedbackPage", feedbackPage);
        return new ModelAndView("/manage/feedback_list", map);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResultVO list(
            @RequestParam(value = "state", defaultValue = "-1") Integer state,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ){
        Pageable request = PageRequest.of(page, size);
        Page<Feedback> feedbackPage;
        if (state == -1){
            feedbackPage = feedbackService.findAll(request);
        }else{
            feedbackPage = feedbackService.findAllWithState(state, request);
        }
        return MessageUtil.success("", feedbackPage.getContent());
    }

    @GetMapping("/countState")
    @ResponseBody
    public ResultVO countState(
            @RequestParam(value = "state", defaultValue = "-1") Integer state
    ){
        if(!FeedbackStates.hasCode(state)){
            return MessageUtil.error("状态不存在", null);
        }else{
//            Feedback feedback = new Feedback();
//            feedback.getFbState()
            long number = feedbackService.countWithState(state);
            return MessageUtil.successDefault(number);
        }
    }

    @GetMapping("/solve")
    @ResponseBody
    public ResultVO solve(
            @RequestParam("fbid") String fbid,
            Map<String, Object> map
    ){
        Boolean result = feedbackService.solveFeedback(fbid);
        if (result){
            return MessageUtil.success();
        }else {
            return MessageUtil.error();
        }
    }

    @GetMapping("/ignore")
    @ResponseBody
    public ResultVO ignore(
            @RequestParam("fbid") String fbid,
            Map<String, Object> map
    ){
        Boolean result = feedbackService.ignoreFeedback(fbid);
        if (result){
            return MessageUtil.success();
        }else {
            return MessageUtil.error();
        }
    }

}
