package com.wgf.shop.controller;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 新增产品
     * @param goods
     * @param file 产品图片
     */
    @RequestMapping(value="/goods",method = RequestMethod.POST)
    @ApiOperation(value="新增产品",notes="POST请求")
    public void insertGoods(@RequestAttribute GoodsModule goods , @RequestParam("file") MultipartFile file){
        System.out.println();
    }
}
