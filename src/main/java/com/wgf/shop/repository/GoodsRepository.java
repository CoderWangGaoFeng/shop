package com.wgf.shop.repository;

import com.wgf.shop.modules.GoodsModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

/**
 * 商品持久层
 */
public interface GoodsRepository extends JpaRepository<GoodsModule ,Serializable> {

    List<GoodsModule> findByAccountIdAndStatus(String accountId ,Boolean status);
}
