package com.wgf.shop.modules;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 订单下商品列表
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name="table_order_goods")
public class OrderGoodsModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String price;

    private Long goodId;

}
