package com.wgf.shop.modules.pay;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Data
@Accessors(chain = true)
public class WxOrderModule {

    private String appid;//小程序id

    private String mch_id;//商户号，微信支付分配的商户号

    private String  nonce_str;//随机32位以内支付串
}
