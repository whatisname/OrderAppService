package com.xxy.ordersystem.service.imp;

import com.xxy.ordersystem.dao.BoothDao;
import com.xxy.ordersystem.entity.Booth;
import com.xxy.ordersystem.enums.BoothStates;
import com.xxy.ordersystem.service.intf.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.service.imp
 * @date 7/13/2018 12:58 AM
 */
@Service
public class BoothServiceImp implements BoothService {

    @Autowired
    private BoothDao boothDao;

    @Override
    public Booth findBoothById(String boothId) {
        return boothDao.findBoothByBIdEquals(boothId);
    }

    @Override
    public List<Booth> findAllBoothByIdIn(List<String> boothIdList) {
        return boothDao.findAllByBIdIn(boothIdList);
    }

    @Override
    public List<Booth> findAllOpenBoothByIdIn(List<String> boothIdList) {
        return boothDao.findAllByBIdInAndBStateEquals(boothIdList, BoothStates.OPEN.getCode());
    }

    @Override
    public List<Booth> findAllBooth() {

        return boothDao.findAll();
    }

    @Override
    public List<Booth> findAllOpenBooth() {

        return boothDao.findAllByBStateEquals(BoothStates.OPEN.getCode());
    }

    @Override
    public Page<Booth> findAllBooth(Pageable pageable) {

        return boothDao.findAll(pageable);
    }

    @Override
    public Page<Booth> findAllOpenBooth(Pageable pageable) {

        return boothDao.findAllByBStateEquals(BoothStates.OPEN.getCode(), pageable);
    }

    @Override
    public Page<Booth> findAllCloseBooth(Pageable pageable) {

        return boothDao.findAllByBStateEquals(BoothStates.CLOSE.getCode(), pageable);
    }

    @Override
    public Page<Booth> findAllRestBooth(Pageable pageable) {

        return boothDao.findAllByBStateEquals(BoothStates.REST.getCode(), pageable);
    }

    @Override
    public Page<Booth> findAllBoothByNameContains(String boothNName, Pageable pageable) {
        return boothDao.findAllByBNameContains(boothNName, pageable);
    }

    @Override
    public Boolean updateBooth(Booth booth) {
        Booth result = boothDao.save(booth);
        if (result != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteBooth(Booth booth) {
        boothDao.delete(booth);
        return true;
    }

    @Override
    public Boolean deleteBoothById(String boothId) {
        Booth booth = boothDao.findBoothByBIdEquals(boothId);
        if(booth == null){
            return false;
        }else{
            return this.deleteBooth(booth);
        }
    }

    @Override
    public Booth addBooth(Booth booth) {
        return boothDao.save(booth);
    }
}
