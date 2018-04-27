package com.wgf.shop.controller;

import com.wgf.shop.modules.AddressModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.AddressService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.web.bind.annotation.*;

/**
 * 地址控制器
 */
@RestController
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * 新增地址
     * @param entity
     * @return
     */
    @RequestMapping(value="/address",method = RequestMethod.POST)
    public ResponseObject insert(@RequestBody AddressModule entity){
        return this.addressService.insert(entity);
    }
}
