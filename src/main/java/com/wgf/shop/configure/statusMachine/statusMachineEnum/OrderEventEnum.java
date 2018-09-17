package com.wgf.shop.configure.statusMachine.statusMachineEnum;

/**
 * 状态机核心事件枚举类
 * @Author wanggaofeng
 * @Date 2018/8/28 16:39
 */
public enum OrderEventEnum {
    CREATORDER("生成订单",0),//生成订单
    ORDERPAY("支付",1),//支付
    ORDERSEND("发货",2),//发货
    ORDEREND("订单结束",3);//确认收货

    private String msg;
    private Integer status;

    OrderEventEnum(String msg ,Integer status){
        this.msg = msg;
        this.status = status;
    }
}
