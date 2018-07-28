package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Booth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/12/2018 11:51 PM
 */

public interface BoothDao extends JpaRepository<Booth, String> {
    List<Booth> findAllByBStateEquals(Integer state);
    Page<Booth> findAllByBStateEquals(Integer state, Pageable pageable);
    List<Booth> findAllByBIdIn(List<String> boothIdList);
    List<Booth> findAllByBIdInAndBStateEquals(List<String> boothIdList, Integer state);
    Booth findBoothByBIdEquals(String boothId);

    Page<Booth> findAllByBNameContains(String boothName, Pageable pageable);

}
