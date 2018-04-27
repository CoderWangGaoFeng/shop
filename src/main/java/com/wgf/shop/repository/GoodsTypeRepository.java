package com.wgf.shop.repository;

import com.wgf.shop.modules.GoodsTypeModule;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface GoodsTypeRepository extends CrudRepository<GoodsTypeModule ,Serializable> {

    List<GoodsTypeModule> findByAccountId(String accountId);
}
