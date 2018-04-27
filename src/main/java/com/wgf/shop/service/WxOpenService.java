package com.wgf.shop.service;

import com.wgf.shop.modules.ResponseObject;

public interface WxOpenService {
    ResponseObject insert(String openId, String sessionId);
}
