package com.wgf.shop.repository;

import com.wgf.shop.modules.GoodsModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query(value="select * from table_goods g where g.id in " +
            "(select good_id from table_order_goods o where o.order_id = ?1)" ,nativeQuery = true)
    List<GoodsModule> findByOrderId(String orderId);

    /**
     * PC端查询商品数据
     * @param accountId
     * @return
     */
    @Query(value="SELECT goods.id , goods.name,goods.price,goods.status,goods.type_id, " +
            "goods.sale_number,type.name AS typeName,goods.img,goods.account_id " +
            "FROM table_goods goods " +
            "LEFT JOIN table_goods_type type ON goods.type_id = type.id " +
            "WHERE goods.account_id = :accountId",nativeQuery=true)
    List<Object> findByAccountId(@Param("accountId")String accountId);
}
