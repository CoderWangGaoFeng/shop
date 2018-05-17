package com.wgf.shop.modules;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户个人信息
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name="table_account_info")
public class AccountInfoModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 用户帐号id
     */
    private String accountId;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 用户头像
     */
    private String img;

    /**
     * 用户logo
     */
    private String logo;

    @Transient
    private String token;

}
