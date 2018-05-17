package com.wgf.shop.repository;

import com.wgf.shop.modules.OrderModule;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value="select * from table_order o order by o.status , " +
            "case o.status when 0 then o.create_time else o.status end desc, " +
            "case o.status when 1 then o.create_time end desc, " +
            "case o.status when 2 then o.create_time end desc limit 5",nativeQuery = true)
    List<OrderModule> findByOpenIdAndAccountIdOrderByCreateTimeDesc(String openId,String accountId);
}
