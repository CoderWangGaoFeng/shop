package com.wgf.shop.modules.enmu;

public enum AccountStatus {

    ACCOUNT_NORMAL(0,"正常"),
    ACCOUNT_FREEZE(1,"冻结");

    private Integer sataus;

    private String msg;

    AccountStatus(Integer status ,String msg){
        this.sataus = status;
        this.msg = msg;
    }
}
