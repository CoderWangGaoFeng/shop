package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;

public interface GoodsService {

    ResponseObject findGoodsListByTypeId(String accountId);
}
