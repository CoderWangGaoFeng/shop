package com.wgf.shop.controller;

import com.wgf.shop.modules.OrderModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.vo.OrderVo;
import com.wgf.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 * @author wanggf
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 新增订单
     * @param entity
     * @return
     */
//    @RequestMapping(value="/order",method = RequestMethod.POST)
    @PostMapping
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
    public ResponseObject findOrder(@RequestParam String openId,
                                    @RequestParam String accountId,
                                    @RequestParam String page){
        return this.orderService.findOrderByPage(openId,accountId,page);
    }
}
