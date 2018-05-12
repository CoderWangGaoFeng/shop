package com.wgf.shop.controller;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.WxOpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags={"微信会话信息管理"})
public class WxOpenController{
    private final WxOpenService wxOpenService;

    /**
     * 用户登录小程序，初始化信息
     * @param openId
     * @param sessionId
     * @return
     */
    @RequestMapping(value="init",method = RequestMethod.GET)
    @ApiOperation(value="保存用户的会话id",notes="GET请求，openid和sessionid")
    public ResponseObject signIn(@RequestParam("openId")String openId,
                                 @RequestParam("sessionId")String sessionId){
        return this.wxOpenService.insert(openId,sessionId);
    }
}
