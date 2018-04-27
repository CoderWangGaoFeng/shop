package com.wgf.shop.modules;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品表
 */
@Entity
@Table(name="table_goods")
@Data
public class GoodsModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 类型
     */
    private Long typeId;

    /**
     *  商品名称
     */
    private String name;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品销量
     */
    private String saleNumber;

    /**
     * 所属用户
     */
    private String accountId;

    /**
     * 商品状态
     */
    private Boolean status;
}
