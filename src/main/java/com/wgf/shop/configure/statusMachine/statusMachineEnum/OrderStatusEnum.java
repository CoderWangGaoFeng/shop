package com.wgf.shop.configure.statusMachine.statusMachineEnum;

import com.wgf.shop.modules.enmu.OrderStatus;

/**
 * 状态机状态枚举类
 * @Author wanggaofeng
 * @Date 2018/8/28 16:40
 */
public enum OrderStatusEnum {
    WAITPAY("待支付",0),//待支付
    WAITSEND("待发货",1),//待发货
    WAITRECEIVE("待收货",2),//待收货
    ENDORDER("订单结束",3);//结束

    private String msg;

    private Integer status;

    OrderStatusEnum(String msg ,Integer status){
        this.msg = msg;
        this.status = status;
    }
}
