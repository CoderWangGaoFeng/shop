package com.wgf.shop.modules.vo;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.OrderGoodsModule;
import com.wgf.shop.modules.OrderModule;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderVo {

    private String accountId;

    private String openId;

    private String addressId;

    private Map<Long,Long> goods;

    private OrderModule order;

    private List<OrderGoodsModule> orderGoods;

    private String time;
}
