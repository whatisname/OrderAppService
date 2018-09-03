package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.FeedbackDao;
import com.xxy.ordersystem.entity.Feedback;
import com.xxy.ordersystem.enums.ExceptionStates;
import com.xxy.ordersystem.enums.FeedbackStates;
import com.xxy.ordersystem.exception.SaleException;
import com.xxy.ordersystem.service.intf.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 9/3/2018 8:10 AM
 */
@Service
@Slf4j
public class FeedbackServiceImp implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;
    @Override
    public Feedback add(Feedback feedback) {
        feedbackDao.save(feedback);
        return feedback;
    }

    @Override
    public Boolean updateState(String feedbackId, Integer state) {
        Feedback feedback = feedbackDao.findByFbId(feedbackId);
        if (feedback.getFbState() == FeedbackStates.SOLVED.getCode()
                || feedback.getFbState() == FeedbackStates.IGNORED.getCode()){
            throw new SaleException(ExceptionStates.UPDATE_FAIL.getCode(), "反馈已解决或忽略，无法更新。");
//            return false;
        }
        feedback.setFbState(state);
        feedbackDao.save(feedback);
        return true;
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {

        return feedbackDao.findAll(pageable);
    }

    @Override
    public Page<Feedback> findAllWithState(Integer feedbackState, Pageable pageable) {

        return feedbackDao.findAllByFbState(feedbackState, pageable);
    }

    @Override
    public Boolean solveFeedback(String feedbackId) {
        this.updateState(feedbackId, FeedbackStates.SOLVED.getCode());
        return true;
    }

    @Override
    public Boolean ignoreFeedback(String feedbackId) {
        this.updateState(feedbackId, FeedbackStates.IGNORED.getCode());
        return true;
    }

    @Override
    public long countWithState(Integer state) {

        return feedbackDao.countFeedbacksByFbState(state);
    }
}
