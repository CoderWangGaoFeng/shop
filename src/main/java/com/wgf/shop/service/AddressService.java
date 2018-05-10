package com.wgf.shop.service;

import com.wgf.shop.modules.AddressModule;
import com.wgf.shop.modules.ResponseObject;

public interface AddressService {

    ResponseObject insert(AddressModule entity);

    ResponseObject findAll(String accountId,String openId);
}
