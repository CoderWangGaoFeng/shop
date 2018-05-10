package com.wgf.shop.modules;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name="table_address")
public class AddressModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
    联系人
     */
    private String name;

    /*
    地址
     */
    private String address;

    private String openId;

    /*
    联系电话
     */
    private String phone;

    /*
    是否默认。true为默认，false为非默认。仅有一个默认地址
     */
    private Boolean status;

    private String accountId;
}
