package com.wgf.shop.repository;

import com.wgf.shop.modules.AddressModule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 收货地址持久层
 */
public interface AddressRepository extends CrudRepository<AddressModule,Serializable> {

    List<AddressModule> findByOpenId(String openId);

    @Modifying
    @Query(value = "update AddressModule  set status = false  where openId = ?1")
    void updateAddressStatus(@Param("openId")String openId);
}
