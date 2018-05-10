package com.wgf.shop.repository;

import com.wgf.shop.modules.OrderModule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 订单持久层
 * @author wanggf
 */
@Transactional
public interface OrderRepository extends CrudRepository<OrderModule,Serializable>{

    List<OrderModule> findByOpenIdAndAccountIdOrderByCreateTimeDesc(String openId,String accountId);
}
