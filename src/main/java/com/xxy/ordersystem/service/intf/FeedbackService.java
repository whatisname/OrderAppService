package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 9/3/2018 8:02 AM
 */
public interface FeedbackService {
    Feedback add(Feedback feedback);

    /**
     * 更改反馈状态
     * 反馈内容无法更改，只允许更改状态。
     * @param feedbackId feedback id
     * @param state 状态
     * @return
     */
    Boolean updateState(String feedbackId, Integer state);
    Page<Feedback> findAll(Pageable pageable);
    Page<Feedback> findAllWithState(Integer feedbackState, Pageable pageable);
    Boolean solveFeedback(String feedbackId);
    Boolean ignoreFeedback(String feedbackId);
    long countWithState(Integer state);

}
