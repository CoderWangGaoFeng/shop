package com.wgf.shop.modules.enmu;

/**
 * 订单状态枚举
 */
public enum OrderStatus {

    WAIT_PAY(0,"待支付"),
    WAIT_SEND(1,"已支付待配送"),
    ORDER_CANCEL(2,"订单结束"),
    END(3,"订单取消"),
    ORDER_RETURN(4,"退货");

    private Integer status;

    private String msg;

    OrderStatus(Integer status , String msg){
        this.msg = msg;
        this.status = status;
    }
}
