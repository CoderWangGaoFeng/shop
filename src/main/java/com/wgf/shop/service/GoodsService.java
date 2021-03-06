package com.wgf.shop.service;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.ResponseObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface GoodsService {

    ResponseObject findGoodsListByTypeId(String accountId);

    ResponseObject saveGoods(MultipartFile file , GoodsModule goods);

    ResponseObject selectGoods(String accountId);

    ResponseObject updateGoods(HttpServletRequest request);
}
