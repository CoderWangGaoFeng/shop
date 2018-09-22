package com.wgf.shop.service;

import com.wgf.shop.modules.OrderModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.vo.OrderVo;

import java.util.List;

public interface OrderService {

    ResponseObject insertOrder(OrderVo entity);

    ResponseObject findOrderByPage(String openId,String accountId,String page);

    ResponseObject delOrder(String id);
}
