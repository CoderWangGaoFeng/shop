package com.wgf.shop.modules;

import com.wgf.shop.modules.enmu.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单实体类
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name="table_order")
public class OrderModule {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid",name="uuid")
    private String id;

    /**
     * 订单状态
     */
    private OrderStatus status;

    /**
     * 订单所属客户openId
     */
    private String openId;

    /**
     * 商店id
     */
    private String accountId;


    private Timestamp createTime;

    /**
     * 订单总价
     */
    private BigDecimal price;

    /**
     * 订单收件人名称
     */
    private String name;

    /**
     * 订单收件人电话
     */
    private String phone;

    /**
     * 订单收件人地址
     */
    private String address;

}
