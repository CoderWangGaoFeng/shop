package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;

public interface GoodsTypeService {

    ResponseObject findListByAccount(String accountId);
}
