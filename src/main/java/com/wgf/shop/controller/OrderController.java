package com.wgf.shop.controller;

import com.wgf.shop.configure.annotation.CheckLogin;
import com.wgf.shop.modules.OrderModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.vo.OrderVo;
import com.wgf.shop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 * @author wanggf
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order")
@Api(tags={"订单管理"})
public class OrderController {

    private final OrderService orderService;

    /**
     * 新增订单
     * @param entity
     * @return
     */
//    @RequestMapping(value="/order",method = RequestMethod.POST)
    @PostMapping
    @ApiOperation(value="新增订单",notes="POST请求")
    public ResponseObject insertOrder(@RequestBody OrderVo entity){
        return this.orderService.insertOrder(entity);
    }

    /**
     * 查询订单
     * @param openId
     * @param accountId
     * @param page
     * @return
     */
    @GetMapping
    @ApiOperation(value="查询用户所有订单",notes="GET请求")
    public ResponseObject findOrder(@RequestParam String openId,
                                    @RequestParam String accountId,
                                    @RequestParam String page){
        return this.orderService.findOrderByPage(openId,accountId,page);
    }

    /**
     * 删除订单
     * @Author wanggaofeng
     * @Date 2018/9/22 9:40
     **/
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value="删除订单",notes = "DELETE请求")
    public ResponseObject delOrder(@RequestParam("id") String id){
        return this.orderService.delOrder(id);
    }
}
