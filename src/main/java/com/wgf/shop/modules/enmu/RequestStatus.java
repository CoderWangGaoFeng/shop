package com.wgf.shop.modules.enmu;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 请求状态枚举
 */
public enum RequestStatus {

    SUCCESS(0,"成功"),
    FAIL(1,"失败");

    private Integer status;
    private String msg;

    RequestStatus(Integer status , String msg){
        this.msg = msg;
        this.status = status;
    }
}
