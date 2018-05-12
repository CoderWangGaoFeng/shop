package com.wgf.shop.controller;

import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.service.AccountService;
import com.wgf.shop.service.imp.AccountServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
@Api(tags={"PC端登陆"})
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ApiOperation(value="pc端用户登录",notes="方法的备注说明")
    public ResponseObject signIn(@RequestParam("userName")String userName ,
                                 @RequestParam("password")String password){
        return this.accountService.signIn(userName,password);
    }
}
