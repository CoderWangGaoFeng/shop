package com.wgf.shop.controller;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(tags={"商品管理"})
public class GoodsController {

    private final GoodsService goodsService;

    /**
     * 产品列表初始话
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/goodses",method = RequestMethod.GET)
    @ApiOperation(value="查询用户所有商品",notes="GET请求")
    public ResponseObject initGoods(@RequestParam("accountId")String accountId ){
        return this.goodsService.findGoodsListByTypeId(accountId);
    }
}
