package com.wgf.shop.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/pay")
@Api(tags={"微信支付"})
public class WxPayController {

    public String orderMessage(){
        return null;
    }
}
