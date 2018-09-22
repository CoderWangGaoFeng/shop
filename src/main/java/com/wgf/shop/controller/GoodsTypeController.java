package com.wgf.shop.controller;

import com.wgf.shop.configure.annotation.CheckLogin;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.GoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品分类控制器
 */
@RestController
@AllArgsConstructor
@Api(tags={"商品分类管理"})
public class GoodsTypeController {

    private final GoodsTypeService goodsTypeService;

    /**
     * 查询商家产品分类
     * @param accountId
     * @return
     */
    @RequestMapping(value="/goodsTypes",method = RequestMethod.GET)
    @ApiOperation(value="查询商户的所有商品分类",notes="GET请求")
    public ResponseObject findListByAccount(@RequestParam("accountId") String accountId){
        return this.goodsTypeService.findListByAccount(accountId);
    }
}
