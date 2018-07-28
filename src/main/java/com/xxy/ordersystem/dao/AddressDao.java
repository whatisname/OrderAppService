package com.xxy.ordersystem.dao;

import com.xxy.ordersystem.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author X
 * @package com.xxy.ordersystem.dao
 * @date 7/13/2018 2:54 PM
 */
public interface AddressDao extends JpaRepository<Address, String> {
    Address findAddressByAId(String adressId);
    Address findAddressBySIdAndADefaultEquals(String studentId, boolean isDefault);
    List<Address> findAllBySId(String studentId);
    List<Address> findAllByAQuyu(int quyuCode);
}
