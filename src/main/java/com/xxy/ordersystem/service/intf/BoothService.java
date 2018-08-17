package com.xxy.ordersystem.service.intf;

import com.xxy.ordersystem.entity.Booth;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.intf
 * @date 7/13/2018 12:12 AM
 */
public interface BoothService {
    Booth findBoothById(String boothId);
    List<Booth> findAllBoothByIdIn(List<String> boothIdList);
    List<Booth> findAllOpenBoothByIdIn(List<String> boothIdList);

    List<Booth> findAllBooth();
    List<Booth> findAllOpenBooth();

    Page<Booth> findAllBooth(Pageable pageable);
    Page<Booth> findAllOpenBooth(Pageable pageable);
    Page<Booth> findAllCloseBooth(Pageable pageable);
    Page<Booth> findAllRestBooth(Pageable pageable);

    Page<Booth> findAllBoothByNameContains(String boothNName, Pageable pageable);

    Boolean updateBooth(Booth booth);

    Boolean deleteBooth(Booth booth);
    Boolean deleteBoothById(String boothId);

    Booth addBooth(Booth booth);

    Boolean disableBoothById(String boothId);
}
