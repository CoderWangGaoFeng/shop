package com.wgf.shop.modules;


import com.wgf.shop.modules.enmu.RequestStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回数据类
 */
@Data
@Accessors(chain = true)
public class ResponseObject implements Serializable{

    private RequestStatus status;

    private String msg;

    private Object data;

    public ResponseObject success(String msg ,Object data){
        this.status = RequestStatus.SUCCESS;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResponseObject fail(String msg,Object data){
        this.status = RequestStatus.FAIL;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResponseObject auth(String msg,Object data){
        this.status = RequestStatus.AUTH;
        this.msg = msg;
        this.data =  data;
        return this;
    }
}
