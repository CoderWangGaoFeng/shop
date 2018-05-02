package com.wgf.shop.modules.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderVo {

    private String accountId;

    private String openId;

    private String addressId;

    private Map<Long,Long> goods;
}
