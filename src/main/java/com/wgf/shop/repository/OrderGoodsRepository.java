package com.wgf.shop.repository;

import com.wgf.shop.modules.OrderGoodsModule;
import com.wgf.shop.modules.OrderModule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 订单的商品列表
 */
@Transactional
public interface OrderGoodsRepository extends CrudRepository<OrderGoodsModule,Serializable>{

}
