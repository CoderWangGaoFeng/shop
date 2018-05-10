package com.wgf.shop.controller;

import com.wgf.shop.pay.WxPay;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    private WxPay wxPay;

    /**
     * 支付
     */
    @GetMapping
    public void test(){
        this.wxPay.sendPost();
    }
}
