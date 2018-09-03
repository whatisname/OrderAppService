package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 9/3/2018 7:55 AM
 */
public interface FeedbackDao extends JpaRepository<Feedback, String> {
    long countFeedbacksByFbState(Integer feedbackState);
    Page<Feedback> findAllByFbState(Integer feedbackState, Pageable pageable);
    Page<Feedback> findAll(Pageable pageable);
    Feedback findByFbId(String feedbackId);

}
