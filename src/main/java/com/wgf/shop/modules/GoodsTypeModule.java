package com.wgf.shop.modules;

import lombok.Data;

import javax.persistence.*;

/**
 * 商品类型表
 */
@Entity
@Table(name="table_goods_type")
@Data
public class GoodsTypeModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String accountId;

    private Boolean status;
}
