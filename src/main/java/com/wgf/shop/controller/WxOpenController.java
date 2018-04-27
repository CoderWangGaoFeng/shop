package com.wgf.shop.controller;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.WxOpenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信信息控制器
 */
@RestController
@AllArgsConstructor
public class WxOpenController{
    private final WxOpenService wxOpenService;

    /**
     * 用户登录小程序，初始化信息
     * @param openId
     * @param sessionId
     * @return
     */
    @RequestMapping(value="init",method = RequestMethod.GET)
    public ResponseObject signIn(@RequestParam("openId")String openId,
                                 @RequestParam("sessionId")String sessionId){
        return this.wxOpenService.insert(openId,sessionId);
    }
}
