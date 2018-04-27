package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;

public interface AccountService {
    public ResponseObject signIn(String userName, String password);
}
