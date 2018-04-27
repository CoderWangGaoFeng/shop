package com.wgf.shop.modules.vo;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.GoodsTypeModule;
import lombok.Data;

import java.util.List;

/**
 * 商品返回类
 */
@Data
public class Goods {

    private List<GoodsTypeModule> type;

    private List<GoodsModule> goods;

}
