package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.vo.Goods;

public interface GoodsService {

    ResponseObject findGoodsListByTypeId(String accountId);
}
