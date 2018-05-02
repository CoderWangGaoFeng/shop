package com.wgf.shop.repository;

import com.wgf.shop.modules.GoodsModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 商品持久层
 */
public interface GoodsRepository extends JpaRepository<GoodsModule ,Serializable> {

    List<GoodsModule> findByAccountIdAndStatus(String accountId ,Boolean status);

    /**
     * 根据id集合查询产品集合
     * @param id
     * @return
     */
    List<GoodsModule> findByIdIn(Set<Long> id);
}