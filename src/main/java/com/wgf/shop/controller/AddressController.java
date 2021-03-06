package com.wgf.shop.controller;

import com.wgf.shop.modules.AddressModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.web.bind.annotation.*;

/**
 * 地址控制器
 */
@RestController
@AllArgsConstructor
@Api(tags={"地址管理"})
public class AddressController {

    private final AddressService addressService;

    /**
     * 新增地址
     * @param entity
     * @return
     */
    @RequestMapping(value="/address",method = RequestMethod.POST)
    @ApiOperation(value="新增收货地址",notes="POST请求")
    public ResponseObject insert(@RequestBody AddressModule entity){
        return this.addressService.insert(entity);
    }

    /**
     * 查询所有地址
     * @param accountId
     * @param openId
     * @return
     */
    @RequestMapping(value="/address",method = RequestMethod.GET)
    @ApiOperation(value="查询所有收货地址",notes="GET请求")
    public ResponseObject findAll(String accountId ,String openId){
        return this.addressService.findAll(accountId,openId);
    }
}
